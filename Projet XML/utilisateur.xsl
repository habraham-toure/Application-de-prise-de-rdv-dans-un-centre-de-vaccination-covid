<?xml version="1.0" encoding="UTF-8"?>
	<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head><title>Utilisateur</title></head>
			<body>
				<div align="centre"><p>Utilisateur</p></div>
			<xsl:apply-templates select="Utilisateur"/>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="Utilisateur">
		<p align="centre">
		<table border="1">
			<tr>
				<td>nom</td>
				<td>prenom</td>
				<td>civil</td>
				<td>date_de_naissance</td>
				<td>numero_de_securite_social</td>
				<td>telephone</td>
				<td>numero_d_adresse</td>
				<td>voie</td>
				<td>ville</td>
				<td>code_postal</td>
			</tr>
			<xsl:for-each select="Utilisateur">
				<tr>	
					<td><xsl:value-of select="nom"/></td>
					<td><xsl:value-of select="prenom"/></td>
					<td><xsl:value-of select="civil"/></td>
					<td><xsl:value-of select="date_de_naissance"/></td>
					<td><xsl:value-of select="numero_de_securite_social"/></td>
					<td><xsl:value-of select="telephone"/></td>
					<td><xsl:value-of select="numero_d_adresse"/></td>
					<td><xsl:value-of select="voie"/></td>
					<td><xsl:value-of select="ville"/></td>
					<td><xsl:value-of select="code_postal"/></td>
				</tr>
			</xsl:for-each>
		</table>
		</p>
	</xsl:template>

</xsl:stylesheet>
