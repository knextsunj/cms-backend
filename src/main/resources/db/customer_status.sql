--------------------------------------------------------
--  DDL for Table CUSTOMER_STATUS
--------------------------------------------------------

  CREATE TABLE "CMS"."CUSTOMER_STATUS"
   (	"ID" NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE ,
	"NAME" VARCHAR2(30 BYTE) COLLATE "USING_NLS_COMP",
	"CREATED_DATE" TIMESTAMP (6),
	"UPDATED_DATE" TIMESTAMP (6),
	"IS_DELETED" CHAR(1 BYTE) COLLATE "USING_NLS_COMP" DEFAULT 'N'
   )  DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION DEFERRED
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index CUSTOMER_STATUS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CMS"."CUSTOMER_STATUS_PK" ON "CMS"."CUSTOMER_STATUS" ("ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table CUSTOMER_STATUS
--------------------------------------------------------

  ALTER TABLE "CMS"."CUSTOMER_STATUS" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "CMS"."CUSTOMER_STATUS" ADD CONSTRAINT "CUSTOMER_STATUS_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  TABLESPACE "USERS"  ENABLE;
