package at.cgsit.train.java.records;

import java.math.BigDecimal;
import java.time.Instant;

record PriceChangedEvent(
   long productId,
   BigDecimal oldPrice,
   BigDecimal newPrice,
   Instant timestamp
) {}
