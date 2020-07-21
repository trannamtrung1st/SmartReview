<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <reviews>
      <codes>
        <xsl:for-each select="//*[@class='review-container']">
          <item>
            <xsl:value-of select="./@data-reviewid"/>
          </item>
        </xsl:for-each>
      </codes>
      <dates>
        <xsl:for-each select="//*[@class='ratingDate']">
          <item>
            <xsl:value-of select="./@title"/>
          </item>
        </xsl:for-each>
      </dates>
      <ratingClasses>
        <xsl:for-each select="//*[contains(@class,'ui_column is-9')]/*[contains(@class,'ui_bubble_rating')]">
          <item>
            <xsl:value-of select="./@class"/>
          </item>
        </xsl:for-each>
      </ratingClasses>
      <reviewTitles>
        <xsl:for-each select="//*[contains(@class,'title')]/span[@class='noQuotes']">
          <item>
            <xsl:value-of select="."/>
          </item>
        </xsl:for-each>
      </reviewTitles>
      <reviewContents>
        <xsl:for-each select="//*[contains(@class,'reviews_text_summary_hsx') and preceding-sibling::*[1][@class!='mgrRspnInline']]//p">
          <item>
            <xsl:value-of select="."/>
          </item>
        </xsl:for-each>
      </reviewContents>
      <usernames>
        <xsl:for-each select="//*[contains(@class,'review-container')]/following::*//*[contains(@class,'info_text') and contains(@onclick,'usernameClick')]">
          <item>
            <xsl:value-of select="."/>
          </item>
        </xsl:for-each>
      </usernames>
      <userImages>
        <xsl:for-each select="//*[contains(@class,'ui_avatar resp')]/img">
          <item>
            <xsl:value-of select="./@data-lazyurl"/>
          </item>
        </xsl:for-each>
      </userImages>
    </reviews>
  </xsl:template>
</xsl:stylesheet>