--------------------------------------------------------
--  DDL for Table CITY
--------------------------------------------------------

  CREATE TABLE "CMS"."CITY"
   (	"ID" NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE ,
	"NAME" VARCHAR2(30 BYTE) COLLATE "USING_NLS_COMP",
	"IS_DELETED" CHAR(1 BYTE) COLLATE "USING_NLS_COMP" DEFAULT 'N',
	"STATE_ID" NUMBER,
	"CREATED_DATE" TIMESTAMP (6),
	"UPDATED_DATE" TIMESTAMP (6)
   )  DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION DEFERRED
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index CITY_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CMS"."CITY_PK" ON "CMS"."CITY" ("ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table CITY
--------------------------------------------------------

  ALTER TABLE "CMS"."CITY" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "CMS"."CITY" ADD CONSTRAINT "CITY_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CITY
--------------------------------------------------------

  ALTER TABLE "CMS"."CITY" ADD CONSTRAINT "FK_CITY_STATE" FOREIGN KEY ("STATE_ID")
	  REFERENCES "CMS"."STATE" ("ID") ENABLE;
