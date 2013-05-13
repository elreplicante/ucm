SELECT
	NOMBRE, 
	EMPLEADOS.DNI,
	CALLE,
	DOMICILIOS."CÓDIGO POSTAL",
	TELÉFONO
FROM
	EMPLEADOS 
LEFT JOIN
	DOMICILIOS
ON
	EMPLEADOS.DNI = DOMICILIOS.DNI
LEFT JOIN  
	TELÉFONOS
ON
	EMPLEADOS.DNI = TELÉFONOS.DNI
ORDER BY
	NOMBRE ASC;