<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/listReview">
    <xsl:for-each select="list/item">
      <div class="list-item">
        <div>
          <img src="{userImage}" style="width:50px;height:50px;vertical-align: middle;margin-right:10px"/>
          <span>
            <xsl:value-of select="username"/>
          </span>
        </div>
        <div>
          <span class="label">Rating: </span>
          <xsl:value-of select="rating"/>
        </div>
        <div>
          <span class="label">Review date: </span>
          <xsl:value-of select="reviewDate"/>
        </div>
        <div>
          <span class="label">
            <xsl:value-of select="title"/>
          </span>
        </div>
        <div>
          <xsl:value-of select="reviewContent"/>
        </div>
        <div>
          <span class="label">Bad reviews: </span>
          <xsl:for-each select="categories/item">
            <span style="color:red">
              <xsl:value-of select="name"/>,
            </span>
          </xsl:for-each>
        </div>
      </div>
    </xsl:for-each>
  </xsl:template>
</xsl:stylesheet>