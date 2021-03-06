/*
 * Copyright 2018 Nathaniel Stickney
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package is.stma.beanpoll.controller;

import is.stma.beanpoll.model.Response;
import is.stma.beanpoll.model.Task;
import is.stma.beanpoll.model.Team;
import is.stma.beanpoll.rules.ResponseRules;
import is.stma.beanpoll.service.ResponseService;
import is.stma.beanpoll.service.TaskService;
import is.stma.beanpoll.service.TeamService;
import org.apache.commons.io.IOUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Servlets;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.io.InputStream;

@Model
public class ResponseController extends AbstractEntityController<Response,
        ResponseRules, ResponseService> {

    @Inject
    private ResponseService service;

    @Inject
    private TaskService taskService;

    @Inject
    private TeamService teamService;

    private Response newResponse;

    private Part uploadedFile;

    @Override
    @Produces
    @Named("newresponse")
    Response getNew() {
        if (newResponse == null) {
            newResponse = new Response();
        }
        return newResponse;
    }

    @Override
    void setNew(Response entity) {
        newResponse = entity;
    }

    @Override
    ResponseService getService() {
        return service;
    }

    @Override
    public void update(Response entity) {
        doUpdate(entity);
    }

    @Override
    public void delete(Response entity) {
        doDelete(entity);
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void uploadResponse(Team team, Task task) {

        if (null != uploadedFile) {

            // Check to see if this team has already responded to this task
            boolean createdNew = false;
            task = taskService.readById(task.getId());
            team = teamService.readById(team.getId());
            Response response = task.getResponseByTeam(team);

            // If this team hasn't responded to this task yet, create a new response
            if (null == response) {
                response = getNew();
                response.setTeam(team);
                response.setTask(task);
                create();
                createdNew = true;
            }

            // Upload the response
            try {
                response.setFileName(Servlets.getSubmittedFileName(uploadedFile));
                response.setFileType(uploadedFile.getContentType());
                response.setFileSize(uploadedFile.getSize());
                InputStream input = uploadedFile.getInputStream();
                byte[] uploaded = IOUtils.toByteArray(input);
                response.setUploaded(uploaded);
                update(response);
            } catch (IOException e) {
                if (createdNew) {
                    delete(response);
                }
                errorOut(e, "File upload failed");
            }
        } else {
            errorOut(null, "No file selected for upload");
        }
    }

    public void downloadResponse(Response response) {
        try {
            Faces.sendFile(response.getUploaded(), response.getFileName(), true);
        } catch (IOException e) {
            errorOut(e, "File download failed");
        }
    }
}
