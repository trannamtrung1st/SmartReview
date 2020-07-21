<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <reviews>
      <xsl:for-each select="//*[@class='lemon--li__373c0__1r9wz margin-b3__373c0__q1DuY padding-b3__373c0__342DA border--bottom__373c0__3qNtD border-color--default__373c0__3-ifU']">
        <item>
          <userImages>
            <xsl:value-of select=".//img[@width='60']/@src"/>
          </userImages>
          <username>
            <xsl:value-of select=".//a[@class='lemon--a__373c0__IEZFH link__373c0__1G70M link-color--inherit__373c0__3dzpk link-size--inherit__373c0__1VFlE' and contains(@href,'/user_details')]"/>
          </username>
          <rating>
            <xsl:value-of select="substring-before(.//div[contains(@aria-label,'star rating')]/@aria-label,' ')"/>
          </rating>
          <reviewDate>
            <xsl:value-of select=".//*[@class='lemon--span__373c0__3997G text__373c0__2Kxyz text-color--mid__373c0__jCeOG text-align--left__373c0__2XGa-']"/>
          </reviewDate>
          <reviewContent>
            <xsl:value-of select=".//p[@class='lemon--p__373c0__3Qnnj text__373c0__2Kxyz comment__373c0__3EKjH text-color--normal__373c0__3xep9 text-align--left__373c0__2XGa-']"/>
          </reviewContent>
          <title></title>
        </item>
      </xsl:for-each>
    </reviews>
  </xsl:template>
</xsl:stylesheet>