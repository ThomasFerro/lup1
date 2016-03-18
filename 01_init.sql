/*==============================================================*/
/* Nom de SGBD :  PostgreSQL 8                                  */
/* Date de création :  22/02/2016 17:28:06                      */
/*==============================================================*/


drop table if exists CREDENTIAL cascade;

drop table if exists DO_EVAL cascade;

drop table if exists EVALUATION cascade;

drop table if exists FLAG cascade;

drop table if exists FORMATION cascade;

drop table if exists INTERNSHIP cascade;

drop table if exists INTERNSHIP_LOG cascade;

drop table if exists INTERVENTION cascade;

drop table if exists IS_ABSENT_DURING cascade;

drop table if exists IS_ALLOWED_TO cascade;

drop table if exists IS_REGISTER_IN cascade;

drop table if exists MANAGE_INTERNSHIP cascade;

drop table if exists MEMBER cascade;

drop table if exists ORGANIZATION cascade;

drop table if exists PROMOTION cascade;

drop table if exists REGISTER_TO_INTERNSHIP cascade;

drop table if exists ROLE cascade;

drop table if exists HAS_ROLE cascade;

drop table if exists SUBJECT cascade;

drop table if exists UE cascade;

drop table if exists UE_PROMOTION cascade;

/*==============================================================*/
/* Table : CREDENTIAL                                           */
/*==============================================================*/
create table CREDENTIAL (
   LOGIN                TEXT                 not null,
   PASSWORD             TEXT                 null,
   MEMBER_ID            INTEGER              not null,
   constraint PK_CREDENTIAL primary key (LOGIN)
);

/*==============================================================*/
/* Table : DO_EVAL                                              */
/*==============================================================*/
create table DO_EVAL (
   MARK                 FLOAT                   DEFAULT 0,
   STUDENT_ID           INTEGER                 not null,
   EVALUATION_ID        INTEGER                 not null,
   constraint PK_DO_EVAL primary key (STUDENT_ID,EVALUATION_ID)
);

/*==============================================================*/
/* Table : EVALUATION                                           */
/*==============================================================*/
create table EVALUATION (
   EVALUATION_ID        SERIAL                  not null,
   NAME                 TEXT                    not null,
   COEFF                FLOAT                   DEFAULT 1,
   TEACHER_ID           INTEGER                 not null,
   SUBJECT_ID           INTEGER                 not null,
   DATE_EVAL            DATE,
   constraint PK_EVALUATION primary key (EVALUATION_ID)
);

/*==============================================================*/
/* Table : FLAG                                                 */
/*==============================================================*/
create table FLAG (
   FLAG_ID              SERIAL               not null,
   LABEL                TEXT                 null,
   constraint PK_FLAG primary key (FLAG_ID)
);

/*==============================================================*/
/* Table : FORMATION                                            */
/*==============================================================*/
create table FORMATION (
   FORMATION_ID         SERIAL               not null,
   NAME                 TEXT                 null,
   SEMESTRE_MIN         INTEGER              not null,
   SEMESTRE_MAX         INTEGER              not null,
   constraint PK_FORMATION primary key (FORMATION_ID)
);

/*==============================================================*/
/* Table : INTERNSHIP                                           */
/*==============================================================*/
create table INTERNSHIP (
   INTERNSHIP_ID        SERIAL               not null,
   TITLE                TEXT                 null,
   MISSIONS             TEXT                 null,
   DESCRIPTION          TEXT                 null,
   TECHNOLOGY           TEXT                 null,
   DURATION             FLOAT                null,
   BEGIN_DATE           DATE                 null,
   SIRET                TEXT                 not null,
   FORMATION_ID         INTEGER                 not null,
   YEAR                 TEXT                    not null,
   constraint PK_INTERNSHIP primary key (INTERNSHIP_ID)
);

/*==============================================================*/
/* Table : INTERNSHIP_LOG                                       */
/*==============================================================*/
create table INTERNSHIP_LOG (
   INTERNSHIP_LOG_ID    SERIAL                  not null,
   DATE_LOG             TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
   QUOTE                TEXT                    null,
   FLAG_ID              INTEGER                 not null,
   MEMBER_ID            INTEGER                 not null,
   INTERNSHIP_ID        INTEGER                 not null,
   constraint PK_INTERNSHIP_LOG primary key (INTERNSHIP_LOG_ID)
);

/*==============================================================*/
/* Table : INTERVENTION                                         */
/*==============================================================*/
create table INTERVENTION (
   INTERVENTION_ID      SERIAL                 not null,
   QUOTE                TEXT                   null,
   DATE_INTERVENTION    DATE                   null,
   INTERVENANT_ID       INTEGER                not null,
   FORMATION_ID         INTEGER                 not null,
   YEAR                 TEXT                    not null,
   constraint PK_INTERVENTION primary key (INTERVENTION_ID)
);

/*==============================================================*/
/* Table : IS_ABSENT_DURING                                     */
/*==============================================================*/
create table IS_ABSENT_DURING (
   STUDENT_ID           INTEGER                 not null,
   SUBJECT_ID           INTEGER                 not null,
   DATE_ABSENT          DATE                    null
);

/*==============================================================*/
/* Table : IS_ALLOWED_TO                                        */
/*==============================================================*/
-- create table IS_ALLOWED_TO (
--    ROLE                 TEXT                 not null,
--    MEMBER_ID            INTEGER                 not null
-- );

/*==============================================================*/
/* Table : IS_REGISTER_IN                                       */
/*==============================================================*/
create table IS_REGISTER_IN (
   STUDENT_ID           INTEGER                 not null,
   FORMATION_ID         INTEGER                 not null,
   YEAR                 TEXT                    not null,
   constraint PK_IS_REGISTER_IN primary key (STUDENT_ID,FORMATION_ID,YEAR)
);

/*==============================================================*/
/* Table : MANAGE_INTERNSHIP                                    */
/*==============================================================*/
create table MANAGE_INTERNSHIP (
   MEMBER_ID            INTEGER                 not null,
   INTERNSHIP_ID        INTEGER                 not null,
   constraint PK_MANAGE_INTERNSHIP primary key (MEMBER_ID,INTERNSHIP_ID)
);

/*==============================================================*/
/* Table : MEMBER                                               */
/*==============================================================*/
create table MEMBER (
   MEMBER_ID            SERIAL               not null,
   FIRST_NAME           TEXT                 null,
   LAST_NAME            TEXT                 null,
   EMAIL                TEXT                 null,
   BIRTHDAY             DATE                 null,
   PHONE                TEXT                 null,
   SIRET                TEXT                 null,
   PICTURE              TEXT                 null,
   ADDRESS              TEXT                 null,
   constraint PK_MEMBER primary key (MEMBER_ID)
);

/*==============================================================*/
/* Table : ORGANIZATION                                         */
/*==============================================================*/
create table ORGANIZATION (
   SIRET                CHAR(14)             not null,
   NAME                 TEXT                 null,
   ADDRESS              TEXT                 null,
   PHONE                TEXT                 null,
   FAX                  TEXT                 null,
   HR                   TEXT                 null,
   constraint PK_ORGANIZATION primary key (SIRET)
);

/*==============================================================*/
/* Table : PROMOTION                                            */
/*==============================================================*/
create table PROMOTION (
   FORMATION_ID         INTEGER                 not null,
   YEAR                 TEXT                    not null,
   RESPONSABLE_ID       INTEGER                 not null,
   constraint PK_PROMOTION primary key (FORMATION_ID,YEAR)
);

/*==============================================================*/
/* Table : REGISTER_TO_INTERNSHIP                               */
/*==============================================================*/
-- Devenu un log type "Inscrit à cette offre"

-- create table REGISTER_TO_INTERNSHIP (
--    INTERNSHIP_ID        INTEGER                 not null,
--    STUDENT_ID            INTEGER                 not null
-- );SIRET

/*==============================================================*/
/* Table : ROLE                                                 */
/*==============================================================*/
create table ROLE (
   ROLE                  TEXT                 not null,
   constraint PK_ROLE primary key (ROLE)
);

/*==============================================================*/
/* Table : HAS_ROLE                                             */
/*==============================================================*/
create table HAS_ROLE (
   LOGIN               TEXT              not null,
   ROLE                TEXT              not null,
   constraint PK_HAS_ROLE primary key (LOGIN,ROLE)
);

/*==============================================================*/
/* Table : SUBJECT                                              */
/*==============================================================*/
create table SUBJECT (
   SUBJECT_ID           SERIAL                  not null,
   NAME                 TEXT                    null,
   COEFF                FLOAT                   null,
   UE_ID                INTEGER                 not null,
   constraint PK_SUBJECT primary key (SUBJECT_ID)
);

/*==============================================================*/
/* Table : UE                                                   */
/*==============================================================*/
create table UE (
   UE_ID                SERIAL               not null,
   NAME                 TEXT                 null,
   constraint PK_UE primary key (UE_ID)
);

/*==============================================================*/
/* Table : UE_PROMOTION                                         */
/*==============================================================*/
create table UE_PROMOTION (
   FORMATION_ID         INTEGER                 not null,
   YEAR                 TEXT                    not null,
   SEMESTER             INTEGER                 not null,
   COEFF                FLOAT                   null,
   UE_ID                INTEGER                 not null,
   constraint PK_UE_PROMOTION primary key (FORMATION_ID,YEAR,SEMESTER,UE_ID)
);

alter table DO_EVAL
   add constraint FK_DO_EVAL_DO_EVAL_EVALUATI foreign key (EVALUATION_ID)
      references EVALUATION (EVALUATION_ID)
      on delete restrict on update cascade;

alter table DO_EVAL
   add constraint FK_DO_EVAL_DO_EVAL2_MEMBER foreign key (STUDENT_ID)
      references MEMBER (MEMBER_ID)
      on delete restrict on update cascade;

alter table EVALUATION
   add constraint FK_EVALUATI_CREATE_EV_MEMBER foreign key (TEACHER_ID)
      references MEMBER (MEMBER_ID)
      on delete restrict on update cascade;

alter table EVALUATION
   add constraint FK_EVALUATI_EVAL_SUBJ_SUBJECT foreign key (SUBJECT_ID)
      references SUBJECT (SUBJECT_ID)
      on delete restrict on update cascade;

alter table INTERNSHIP
   add constraint FK_INTERNSH_CREATE_ORGANIZA foreign key (SIRET)
      references ORGANIZATION (SIRET)
      on delete restrict on update cascade;

alter table INTERNSHIP
 add constraint FK_INTERNSH_FORM_YEAR foreign key (FORMATION_ID,YEAR)
    references PROMOTION (FORMATION_ID,YEAR)
    on delete restrict on update cascade;

alter table INTERNSHIP_LOG
   add constraint FK_INTERNSH_IS_FLAGGE_FLAG foreign key (FLAG_ID)
      references FLAG (FLAG_ID)
      on delete restrict on update cascade;

alter table INTERNSHIP_LOG
   add constraint FK_INTERNSH_REFERENCE_INTERNSH foreign key (INTERNSHIP_ID)
      references INTERNSHIP (INTERNSHIP_ID)
      on delete restrict on update cascade;

alter table INTERNSHIP_LOG
   add constraint FK_INTERNSH_UPDATE_IN_MEMBER foreign key (MEMBER_ID)
      references MEMBER (MEMBER_ID)
      on delete restrict on update cascade;

alter table INTERVENTION
   add constraint FK_INTERVEN_INTERVENA_MEMBER foreign key (INTERVENANT_ID)
      references MEMBER (MEMBER_ID)
      on delete restrict on update cascade;

alter table INTERVENTION
   add constraint FK_INTERVEN_FORMATION_YEAR foreign key (FORMATION_ID,YEAR)
      references PROMOTION (FORMATION_ID,YEAR)
      on delete restrict on update cascade;

alter table IS_ABSENT_DURING
   add constraint FK_IS_ABSEN_IS_ABSENT_SUBJECT foreign key (SUBJECT_ID)
      references SUBJECT (SUBJECT_ID)
      on delete restrict on update cascade;

alter table IS_ABSENT_DURING
   add constraint FK_IS_ABSEN_IS_ABSENT_MEMBER foreign key (STUDENT_ID)
      references MEMBER (MEMBER_ID)
      on delete restrict on update cascade;

-- alter table IS_ALLOWED_TO
--    add constraint FK_IS_ALLOW_IS_ALLOWE_MEMBER foreign key (MEMBER_ID)
--       references MEMBER (MEMBER_ID)
--       on delete restrict on update cascade;
--
-- alter table IS_ALLOWED_TO
--    add constraint FK_IS_ALLOW_IS_ALLOWE_ROLE foreign key (ROLE)
--       references ROLE (ROLE)
--       on delete restrict on update cascade;

alter table IS_REGISTER_IN
   add constraint FK_IS_REGIS_FORMATION_YEAR foreign key (FORMATION_ID,YEAR)
      references PROMOTION (FORMATION_ID,YEAR)
      on delete restrict on update cascade;

alter table IS_REGISTER_IN
   add constraint FK_IS_REGIS_IS_REGIST_MEMBER foreign key (STUDENT_ID)
      references MEMBER (MEMBER_ID)
      on delete restrict on update cascade;

alter table MANAGE_INTERNSHIP
   add constraint FK_MANAGE_I_MANAGE_IN_MEMBER foreign key (MEMBER_ID)
      references MEMBER (MEMBER_ID)
      on delete restrict on update cascade;

alter table MANAGE_INTERNSHIP
   add constraint FK_MANAGE_I_MANAGE_IN_INTERNSH foreign key (INTERNSHIP_ID)
      references INTERNSHIP (INTERNSHIP_ID)
      on delete restrict on update cascade;

alter table MEMBER
   add constraint FK_MEMBER_WORK_IN_ORGANIZA foreign key (SIRET)
      references ORGANIZATION (SIRET)
      on delete restrict on update cascade;

alter table CREDENTIAL
    add constraint FK_CREDENTIAL_MEMBER_ID foreign key (MEMBER_ID)
        references MEMBER (MEMBER_ID)
        on delete restrict on update cascade;

alter table HAS_ROLE
 add constraint FK_HAS_ROLE_LOGIN foreign key (LOGIN)
    references CREDENTIAL (LOGIN)
    on delete restrict on update cascade;

alter table HAS_ROLE
   add constraint FK_HAS_ROLE_ROLE foreign key (ROLE)
      references ROLE (ROLE)
      on delete restrict on update cascade;

alter table PROMOTION
   add constraint FK_PROMOTIO_INHERITS_FORMATIO foreign key (FORMATION_ID)
      references FORMATION (FORMATION_ID)
      on delete restrict on update cascade;

alter table PROMOTION
   add constraint FK_PROMOTIO_MANAGE_MEMBER foreign key (RESPONSABLE_ID)
      references MEMBER (MEMBER_ID)
      on delete restrict on update cascade;

-- alter table REGISTER_TO_INTERNSHIP
--    add constraint FK_REGISTER_REGISTER__MEMBER foreign key (STUDENT_ID)
--       references MEMBER (MEMBER_ID)
--       on delete restrict on update cascade;

-- alter table REGISTER_TO_INTERNSHIP
--    add constraint FK_REGISTER_REGISTER__INTERNSH foreign key (INTERNSHIP_ID)
--       references INTERNSHIP (INTERNSHIP_ID)
--       on delete restrict on update cascade;

alter table SUBJECT
   add constraint FK_SUBJECT_BELONG_TO_UE foreign key (UE_ID)
      references UE (UE_ID)
      on delete restrict on update cascade;

alter table UE_PROMOTION
   add constraint FK_UE_PROMO_UE_PROMOT_UE foreign key (UE_ID)
      references UE (UE_ID)
      on delete restrict on update cascade;

alter table UE_PROMOTION
   add constraint FK_UE_PROMO_FORMATION_YEAR foreign key (FORMATION_ID,YEAR)
      references PROMOTION (FORMATION_ID,YEAR)
      on delete restrict on update cascade;
