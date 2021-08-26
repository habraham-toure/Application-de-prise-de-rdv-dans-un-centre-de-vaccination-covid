<?xml version="1.0" encoding="UTF-8"?>
	<xsl:template match="/">
		<html>
			<head><title>Rdv</title></head>
			<body>
				<div align="centre"><p>Rendez-Vous</p></div>
			<xsl:apply-templates select="Rdv"/>
			</body>
		</htm;>
	</xsl:template>

	<xsl:template match="Rdv">
		<p align="centre">
		<table border="1">
			<tr>
				<td>numero_de_securite_social</td>
				<td>nom</td>
				<td>date</td>
			</tr>
			<xsl:for-each select="Rdv">
				<tr>
					<td><xsl:value-of select="numero_de_securite_social"/></td>
					<td><xsl:value-of select="nom"/></td>
					<td><xsl:value-of select="date"/></td>
				</tr>
			</xsl>
	</xsl:template>