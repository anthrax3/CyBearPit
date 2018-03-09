-- noinspection SqlNoDataSourceInspectionForFile

--
-- JBoss, Home of Professional Open Source
-- Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements
insert into Contest (id, name) values ('C0', 'Cyber Patriot')
insert into Contest (id, name) values ('C1', 'CCDC')
insert into Contest (id, name) values ('C2', 'CDX')
insert into Team (id, name, flag, contest_id) values ('T0', 'Blue Man Group', 'Ready, Go!', 'C0')
insert into Team (id, name, flag, contest_id) values ('T1', 'Bala Morgab', 'BMG', 'C0')
insert into Team (id, name, flag, contest_id) values ('T2', 'General Motors Bicyclists', 'GMb', 'C1')
insert into Team (id, name, flag, contest_id) values ('T3', 'Greater Midwest Baseball', 'GMB', 'C1')
insert into Team (id, name, flag, contest_id) values ('T4', 'Totally Unrelated People', '123456', 'C2')
insert into Team (id, name, flag, contest_id) values ('T5', 'Totally Unrelated People', '123456', 'C2')
insert into Team (id, name, flag, contest_id) values ('T6', 'Totally Unrelated People', '123456', 'C0')
insert into Team (id, name, flag) values ('T7', 'Totally Unrelated People', '123456')
insert into Resource (id, name, contest_id) values ('R0', 'T0 DNS', 'C0')
insert into Resource (id, name, contest_id) values ('R1', 'T1 DNS', 'C0')
insert into Resource (id, name, contest_id) values ('R2', 'T0 HTTP', 'C0')
insert into Resource (id, name, contest_id) values ('R3', 'T1 HTTP', 'C0')
insert into Resource (id, name, contest_id) values ('R4', 'T2 DNS', 'C1')
insert into Resource (id, name, contest_id) values ('R5', 'T3 DNS', 'C1')
insert into Resource (id, name, contest_id) values ('R6', 'T2 HTTP', 'C1')
insert into Resource (id, name, contest_id) values ('R7', 'T3 HTTP', 'C1')
insert into Resource (id, name, contest_id) values ('R8', 'T4 DNS', 'C2')
insert into Resource (id, name, contest_id) values ('R9', 'T5 DNS', 'C2')
insert into Resource (id, name, contest_id) values ('R10', 'T4 HTTP', 'C2')
insert into Resource (id, name, contest_id) values ('R11', 'T5 HTTP', 'C2')