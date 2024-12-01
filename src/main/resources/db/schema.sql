CREATE TABLE BATCH_JOB_INSTANCE  (
    JOB_INSTANCE_ID BIGINT  NOT NULL PRIMARY KEY ,
    VERSION BIGINT ,
    JOB_NAME VARCHAR(100) NOT NULL,
    JOB_KEY VARCHAR(32) NOT NULL,
    constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION  (
    JOB_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
    VERSION BIGINT  ,
    JOB_INSTANCE_ID BIGINT NOT NULL,
    CREATE_TIME DATETIME(6) NOT NULL,
    START_TIME DATETIME(6) DEFAULT NULL ,
    END_TIME DATETIME(6) DEFAULT NULL ,
    STATUS VARCHAR(10) ,
    EXIT_CODE VARCHAR(2500) ,
    EXIT_MESSAGE VARCHAR(2500) ,
    LAST_UPDATED DATETIME(6),
    constraint JOB_INST_EXEC_FK foreign key (JOB_INSTANCE_ID)
        references BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION_PARAMS  (
    JOB_EXECUTION_ID BIGINT NOT NULL ,
    PARAMETER_NAME VARCHAR(100) NOT NULL ,
    PARAMETER_TYPE VARCHAR(100) NOT NULL ,
    PARAMETER_VALUE VARCHAR(2500) ,
    IDENTIFYING CHAR(1) NOT NULL ,
    constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
        references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION  (
    STEP_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
    VERSION BIGINT NOT NULL,
    STEP_NAME VARCHAR(100) NOT NULL,
    JOB_EXECUTION_ID BIGINT NOT NULL,
    CREATE_TIME DATETIME(6) NOT NULL,
    START_TIME DATETIME(6) DEFAULT NULL ,
    END_TIME DATETIME(6) DEFAULT NULL ,
    STATUS VARCHAR(10) ,
    COMMIT_COUNT BIGINT ,
    READ_COUNT BIGINT ,
    FILTER_COUNT BIGINT ,
    WRITE_COUNT BIGINT ,
    READ_SKIP_COUNT BIGINT ,
    WRITE_SKIP_COUNT BIGINT ,
    PROCESS_SKIP_COUNT BIGINT ,
    ROLLBACK_COUNT BIGINT ,
    EXIT_CODE VARCHAR(2500) ,
    EXIT_MESSAGE VARCHAR(2500) ,
    LAST_UPDATED DATETIME(6),
    constraint JOB_EXEC_STEP_FK foreign key (JOB_EXECUTION_ID)
        references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT  (
    STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
    SHORT_CONTEXT VARCHAR(2500) NOT NULL,
    SERIALIZED_CONTEXT TEXT ,
    constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
        references BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT  (
    JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
    SHORT_CONTEXT VARCHAR(2500) NOT NULL,
    SERIALIZED_CONTEXT TEXT ,
    constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
        references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;


    ID BIGINT NOT NULL,
    UNIQUE_KEY CHAR(1) NOT NULL,
    constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_STEP_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_STEP_EXECUTION_SEQ);

CREATE TABLE BATCH_JOB_EXECUTION_SEQ (
    ID BIGINT NOT NULL,
    UNIQUE_KEY CHAR(1) NOT NULL,
    constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_JOB_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_EXECUTION_SEQ);

CREATE TABLE BATCH_JOB_SEQ (
    ID BIGINT NOT NULL,
    UNIQUE_KEY CHAR(1) NOT NULL,
    constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_JOB_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_SEQ);

CREATE TABLE IF NOT EXISTS business_status (
    id                                          BIGINT AUTO_INCREMENT            NOT NULL    comment '영업 상태 id',
    business_status_code                        VARCHAR(255)                     NULL        comment '영업상태구분코드',
    business_status_name                        VARCHAR(255)                     NULL        comment '영업상태명',
    detailed_business_status_code               VARCHAR(255)                     NULL        comment '상세영업상태코드',
    detailed_business_status_name               VARCHAR(255)                     NULL        comment '상세영업상태명',
    PRIMARY KEY (id),
    UNIQUE KEY (business_status_code)
) comment = '영업 상태' ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

INSERT INTO business_status (business_status_code, business_status_name, detailed_business_status_code, detailed_business_status_name)
values ('03', '폐업', '02', '폐업'),
       ('01', '영업/정상', '01', '영업')
;

CREATE TABLE IF NOT EXISTS business_type (
    id                                          BIGINT AUTO_INCREMENT            NOT NULL    comment '영업 상태 id',
    open_service_name                           VARCHAR(255)                     NULL        comment '개방서비스명',
    open_service_id                             VARCHAR(255)                     NULL        comment '개방서비스아이디',
    PRIMARY KEY (id),
    UNIQUE KEY (open_service_id)
    ) comment = '업종 종류' ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

INSERT INTO business_type (open_service_name, open_service_id)
values ('일반음식점', '07_24_04_P')
;

CREATE TABLE IF NOT EXISTS restaurant (
    id                                          BIGINT AUTO_INCREMENT            NOT NULL    comment '가게 id',
    business_status_id                          BIGINT                           NOT NULL    comment '영업 상태 id',
    open_local_government_code                  VARCHAR(255)                     NULL        comment '개방자치단체코드',
    management_number                           VARCHAR(255)                     NULL        comment '관리번호',
    approval_date                               VARCHAR(255)                     NULL        comment '인허가일자',
    license_cancellation_date                   VARCHAR(255)                     NULL        comment '인허가취소일자',
    closure_date                                VARCHAR(255)                     NULL        comment '폐업일자',
    suspension_start_date                       VARCHAR(255)                     NULL        comment '휴업시작일자',
    suspension_end_date                         VARCHAR(255)                     NULL        comment '휴업종료일자',
    reopening_date                              VARCHAR(255)                     NULL        comment '재개업일자',
    business_name                               VARCHAR(255)                     NULL        comment '사업장명',
    last_modified_time                          VARCHAR(255)                     NULL        comment '최종수정시점',
    data_update_type                            VARCHAR(255)                     NULL        comment '데이터갱신구분',
    data_update_date                            VARCHAR(255)                     NULL        comment '데이터갱신일자',
    business_category_name                      VARCHAR(255)                     NULL        comment '업태구분명',
    sanitation_type_name                        VARCHAR(255)                     NULL        comment '위생업태명',
    PRIMARY KEY (id),
    UNIQUE KEY (management_number)
) comment = '전국 음식점' ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS restaurant_address (
    id                                          BIGINT AUTO_INCREMENT            NOT NULL    comment '영업 상태 id',
    food_store_id                               BIGINT                           NOT NULL    comment '전국 음식점 id',
    location_phone                              VARCHAR(255)                     NULL        comment '소재지전화',
    location_area                               VARCHAR(255)                     NULL        comment '소재지면적',
    location_postal_code                        VARCHAR(255)                     NULL        comment '소재지우편번호',
    location_full_address                       VARCHAR(255)                     NULL        comment '소재지전체주소',
    roadName_full_address                       VARCHAR(255)                     NULL        comment '도로명전체주소',
    roadName_postal_code                        VARCHAR(255)                     NULL        comment '도로명우편번호',
    coordinate_x                                VARCHAR(255)                     NULL        comment '좌표정보(x)',
    coordinate_y                                VARCHAR(255)                     NULL        comment '좌표정보(y)',
    PRIMARY KEY (id)
) comment = '가게 주소' ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS restaurant_option (
    id                                          BIGINT AUTO_INCREMENT            NOT NULL    comment '영업 상태 id',
    food_store_id                               BIGINT                           NOT NULL    comment '전국 음식점 id',
    male_employee_count                         VARCHAR(255)                     NULL        comment '남성종사자수',
    female_employee_count                       VARCHAR(255)                     NULL        comment '여성종사자수',
    business_area_type_name                     VARCHAR(255)                     NULL        comment '영업장주변구분명',
    grade_type_name                             VARCHAR(255)                     NULL        comment '등급구분명',
    water_supply_type_name                      VARCHAR(255)                     NULL        comment '급수시설구분명',
    total_employee_count                        VARCHAR(255)                     NULL        comment '총직원수',
    headquarters_employee_count                 VARCHAR(255)                     NULL        comment '본사직원수',
    factory_office_employee_count               VARCHAR(255)                     NULL        comment '공장사무직직원수',
    factory_sales_employee_count                VARCHAR(255)                     NULL        comment '공장판매직직원수',
    factory_production_employee_count           VARCHAR(255)                     NULL        comment '공장생산직직원수',
    building_ownership_type_name                VARCHAR(255)                     NULL        comment '건물소유구분명',
    guarantee_amount                            VARCHAR(255)                     NULL        comment '보증액',
    monthly_rent                                VARCHAR(255)                     NULL        comment '월세액',
    isMulti_use_facility                        VARCHAR(255)                     NULL        comment '다중이용업소여부',
    total_facility_scale                        VARCHAR(255)                     NULL        comment '시설총규모',
    traditional_business_designation_number     VARCHAR(255)                     NULL        comment '전통업소지정번호',
    traditional_business_main_food              VARCHAR(255)                     NULL        comment '전통업소주된음식',
    website                                     VARCHAR(255)                     NULL        comment '홈페이지',
    PRIMARY KEY (id)
) comment = '가게 옵션' ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;
