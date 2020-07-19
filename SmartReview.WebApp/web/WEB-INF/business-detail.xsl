<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/businessDetailModel">
    <xsl:apply-templates select="business" />
    <h2>Review overview</h2>
    <xsl:apply-templates select="reviewGeneral" />
  </xsl:template>
  <xsl:template match="business">
    <h1 style="color:red;">
      <xsl:value-of select="//name"/>
    </h1>
    <div>
      <xsl:for-each select="//images/item">
        <img src="{./imageUrl}" style="width: 300px;height:200px"/>
      </xsl:for-each>
    </div>
    <div>
      <span class="label">Total review:</span>
      <xsl:value-of select="//totalReview"/>
    </div>
    <div>
      <span class="label">Rating:</span>
      <xsl:value-of select="//rating"/>
    </div>
    <div>
      <span class="label">Address:</span>
      <xsl:value-of select="//address"/>
    </div>
    <div>
      <span class="label">Phone:</span>
      <xsl:value-of select="//phone"/>
    </div>
    <div>
      <span class="label">From page:</span>
      <a href="{//fromPage}">
        <xsl:value-of select="//fromPage"/>
      </a>
    </div>
    <div>
      <span class="label">Source detail page:</span>
      <a href="{//detailUrl}">Click here</a>
    </div>
  </xsl:template>
  <xsl:template match="reviewGeneral">
    <div>
      <span class="label">Overall:</span>
      <xsl:choose>
        <xsl:when test="overall='true'">
          Good
        </xsl:when>
        <xsl:otherwise>
          Bad
        </xsl:otherwise>
      </xsl:choose>
    </div>
    <div>
      <span class="label">Bad reviews: </span>
      <br/>
      <xsl:for-each select="badReviewDetails/item">
        <xsl:value-of select="ratio*100"/>%
        <xsl:value-of select="reviewCateName"/>
        (<xsl:value-of select="totalReview"/>)<br/>
      </xsl:for-each>
    </div>
  </xsl:template>
</xsl:stylesheet>