package at.cgsit.train.java.mv.firma;

import at.cgsit.train.java.mv.firma.implementierung.FirmaImplIterator;

public class FirmaIteratorenTest extends  FirmaTest  {


  @Override
  public void createFirmaInstance() {
    super.firma = new FirmaImplIterator("Testfirma Iterator IMple");
  }
}
