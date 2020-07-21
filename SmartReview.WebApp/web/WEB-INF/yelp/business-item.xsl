<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <xsl:param name="url"/>
    <xsl:param name="code"/>
    <businessItem>
      <name>
        <xsl:value-of select="//*[@class='lemon--h1__373c0__2ZHSL heading--h1__373c0__dvYgw undefined heading--inline__373c0__10ozy']"/>
      </name>
      <code>
        <xsl:value-of select="$code"/>
      </code>
      <detailUrl>
        <xsl:value-of select="$url"/>
      </detailUrl>
      <totalReview>
        <xsl:value-of select="substring-before(//*[@class='lemon--p__373c0__3Qnnj text__373c0__2Kxyz text-color--mid__373c0__jCeOG text-align--left__373c0__2XGa- text-size--large__373c0__3t60B'],' ')"/>
      </totalReview>
      <rating>
        <xsl:value-of select="substring-before(//*[@class='lemon--div__373c0__1mboc arrange__373c0__2C9bH gutter-1-5__373c0__2vL-3 vertical-align-middle__373c0__1SDTo margin-b1__373c0__1khoT border-color--default__373c0__3-ifU']//*[contains(@aria-label,'star rating')]/@aria-label,' ')"/>
      </rating>
      <address>
        <xsl:value-of select="//*[@class='lemon--address__373c0__2sPac']"/>
      </address>
      <phone>
        <xsl:value-of select="//*[@class='lemon--p__373c0__3Qnnj text__373c0__2Kxyz text-color--normal__373c0__3xep9 text-align--left__373c0__2XGa- text--offscreen__373c0__2NIm_' and contains(text(),'Phone')]/following::p[1]"/>
      </phone>
      <images>
        <xsl:for-each select="//*[@class='lemon--a__373c0__IEZFH link__373c0__1G70M link-color--blue-dark__373c0__85-Nu link-size--default__373c0__7tls6']/img/@src">
          <item>
            <xsl:value-of select="."/>
          </item>
        </xsl:for-each>
      </images>
    </businessItem>
  </xsl:template>
</xsl:stylesheet>