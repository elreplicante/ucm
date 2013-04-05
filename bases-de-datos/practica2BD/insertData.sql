INSERT ALL

-- Inserta empleados
INTO EMPLEADOS
  (
    DNI,
    NOMBRE,
    SUELDO,
    "FECHA NACIMIENTO"
  )
  VALUES
  (
    '12345678A',
    'Antonio Arjona',
    '5000',
    TO_DATE('23/10/1976','DD/MM/YYYY')
  )
INTO EMPLEADOS
  (
    DNI,
    NOMBRE,
    SUELDO,
    "FECHA NACIMIENTO"
  )
  VALUES
  (
    '12345678C',
    'Carlota Cerezo',
    '1000',
    TO_DATE('01/05/1983','DD/MM/YYYY')
  )
  
INTO EMPLEADOS
  (
    DNI,
    NOMBRE,
    SUELDO,
    "FECHA NACIMIENTO"
  )
  VALUES
  (
    '12345678L',
    'Laura López',
    '1500,25',
    TO_DATE('26/04/1990','DD/MM/YYYY')
  )
  
INTO EMPLEADOS
  (
    DNI,
    NOMBRE,
    SUELDO,
    "FECHA NACIMIENTO"
  )
  VALUES
  (
    '12345678P',
    'Pedro Pérez',
    '2000',
    TO_DATE('04/09/1998','DD/MM/YYYY')
  )
SELECT * FROM DUAL;

INSERT ALL
-- Inserta códigos postales
INTO "CÓDIGOS POSTALES"
  (
    "CÓDIGO POSTAL",
    POBLACIÓN,
    PROVINCIA
  )
  VALUES
  (
    '08050',
    'Parets',
    'Barcelona'
  )
  
INTO "CÓDIGOS POSTALES"
  (
    "CÓDIGO POSTAL",
    POBLACIÓN,
    PROVINCIA
  )
  VALUES
  (
    '14200',
    'Peñarroya',
    'Córdoba'
  )

INTO "CÓDIGOS POSTALES"
  (
    "CÓDIGO POSTAL",
    POBLACIÓN,
    PROVINCIA
  )
  VALUES
  (
    '14900',
    'Lucena',
    'Córdoba'
  )

INTO "CÓDIGOS POSTALES"
  (
    "CÓDIGO POSTAL",
    POBLACIÓN,
    PROVINCIA
  )
  VALUES
  (
    '28040',
    'Madrid',
    'Madrid'
  )

INTO "CÓDIGOS POSTALES"
  (
    "CÓDIGO POSTAL",
    POBLACIÓN,
    PROVINCIA
  )
  VALUES
  (
    '50008',
    'Zaragoza',
    'Zaragoza'
  )

INTO "CÓDIGOS POSTALES"
  (
    "CÓDIGO POSTAL",
    POBLACIÓN,
    PROVINCIA
  )
  VALUES
  (
    '28004',
    'Arganda',
    'Madrid'
  )
SELECT * FROM DUAL;

INSERT ALL

--Inserta teléfonos
INTO TELÉFONOS
  (
    DNI,
    TELÉFONO
  )
  VALUES
  (
    '12345678C',
    '611111111'
  )
  
INTO TELÉFONOS
  (
    DNI,
    TELÉFONO
  )
  VALUES
  (
    '12345678C',
    '931111111'
  )

INTO TELÉFONOS
  (
    DNI,
    TELÉFONO
  )
  VALUES
  (
    '12345678L',
    '913111111'
  )

INTO TELÉFONOS
  (
    DNI,
    TELÉFONO
  )
  VALUES
  (
    '12345678P',
    '913111111'
  )
  
INTO TELÉFONOS
  (
    DNI,
    TELÉFONO
  )
  VALUES
  (
    '12345678P',
    '644444444'
  )

SELECT * FROM DUAL;

INSERT ALL


--Inserta domicilios

INTO DOMICILIOS
  (
    DNI,
    CALLE,
    "CÓDIGO POSTAL"
  )
  VALUES
  (
    '12345678A',
    'Avda. Complutense',
    '28040'
  )

INTO DOMICILIOS
  (
    DNI,
    CALLE,
    "CÓDIGO POSTAL"
  )
  VALUES
  (
    '12345678A',
    'Cántaro',
    '28004'
  )

INTO DOMICILIOS
  (
    DNI,
    CALLE,
    "CÓDIGO POSTAL"
  )
  VALUES
  (
    '12345678P',
    'Diamante',
    '14200'
  )
  
INTO DOMICILIOS
  (
    DNI,
    CALLE,
    "CÓDIGO POSTAL"
  )
  VALUES
  (
    '12345678P',
    'Carbón',
    '14900'
  )

INTO DOMICILIOS
  (
    DNI,
    CALLE,
    "CÓDIGO POSTAL"
  )
  VALUES
  (
    '12345678L',
    'Diamante',
    '14200'
  )

SELECT * FROM dual;
