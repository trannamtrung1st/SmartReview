<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <xsl:param name="url"/>
    <xsl:param name="code"/>
    <businessItem>
      <name>
        <xsl:value-of select="//*[@class='_3a1XQ88S']"/>
      </name>
      <code>
        <xsl:value-of select="$code"/>
      </code>
      <detailUrl>
        <xsl:value-of select="$url"/>
      </detailUrl>
      <totalReview>
        <xsl:value-of select="//*[@class='_3Wub8auF']/text()[1]"/>
      </totalReview>
      <rating>
        <xsl:value-of select="//*[@class='r2Cf69qf']/text()[1]"/>
      </rating>
      <address>
        <xsl:value-of select="//*[@class='_15QfMZ2L' and @href='#MAPVIEW']"/>
      </address>
      <phone>
        <xsl:value-of select="//*[@class='_3S6pHEQs' and contains(@href,'tel')]"/>
      </phone>
      <images>
        <xsl:for-each select="//img[@class='basicImg' and contains(@src,'.')]">
          <item>
            <xsl:value-of select="./@src"/>
          </item>
        </xsl:for-each>
      </images>
    </businessItem>
  </xsl:template>
</xsl:stylesheet>