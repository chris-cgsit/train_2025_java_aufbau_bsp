package at.cgsit.train.java.mv.firma;

import at.cgsit.train.java.mv.firma.implementierung.FirmaImplIterator;
import at.cgsit.train.java.mv.firma.implementierung.FirmaImplStream;

public class FirmaStreamingTest extends  FirmaTest  {

  @Override
  public void createFirmaInstance() {
    firma = new FirmaImplStream("Testfirma Stream Impl ");
  }
}
