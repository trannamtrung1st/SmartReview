<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="contextPath"/>
    <xsl:template match="/">
        <xsl:for-each select="//item">
            <div class="list-item">
                <h3>
                    <a href="{$contextPath}/business/{id}">
                        <xsl:value-of select="name"/>
                    </a>
                </h3>
                <div>
                    <span class="label">Total review:</span> 
                    <xsl:value-of select="totalReview"/>
                </div>
                <div>
                    <span class="label">Rating:</span> 
                    <xsl:value-of select="rating"/>
                </div>
                <div>
                    <span class="label">Address:</span> 
                    <xsl:value-of select="address"/>
                </div>
                <div>
                    <span class="label">Phone:</span> 
                    <xsl:value-of select="phone"/>
                </div>
                <div>
                    <span class="label">From page:</span> 
                    <a href="{fromPage}">
                        <xsl:value-of select="fromPage"/>
                    </a>
                </div>
            </div>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>