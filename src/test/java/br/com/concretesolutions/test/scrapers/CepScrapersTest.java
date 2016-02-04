/*
* Copyright 2016 Redecard S.A.
*************************************************************
*Nome     : CepScrapersTest.java
*Autor    : jfelipesp
*Data     : 4 de fev de 2016
*Empresa  : Concrete Solutions / Redecard
*************************************************************
*/
package br.com.concretesolutions.test.scrapers;

import br.com.concretesolutions.api.models.CepResult;
import br.com.concretesolutions.scrapers.CepScraper;

import org.junit.Test;

/**
 * @author jfelipesp
 *
 */
public class CepScrapersTest {
  
  @Test(expected = IllegalStateException.class)
  public void testListSecurityKeysSuccess() throws Exception{
    
    
    CepResult result =  CepScraper.getPostalcodeResult("99999999");
    
  }

}
