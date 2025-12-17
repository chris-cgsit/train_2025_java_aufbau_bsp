/*
 *  Copyright © 2025 CGS IT Solutions GmbH.
 *  All rights reserved.
 *
 *  This source code is proprietary intellectual property
 *  of CGS IT Solutions GmbH and is provided solely for use
 *  within licensed training programs.
 *
 *   Any copying, redistribution, modification, or use
 *   beyond the permitted scope is strictly prohibited.
 */

package at.cgsit.train.java.patterns.strategy;

// das ist eine normale source code doku

/**
 * das hier ist java dok dokumenation. <br> Der Standard
 * und auch input für die generierten HMLT javadoc seiten.
 * <hr>
 * hier sind auch einige html elemente möglich, siehe java doc jvm spec
 * <br>
 * das CcheckoutService läuft ohne zu wissen welche implementierung
 * genau hinter der Payment Strategy wirklcih liegt.
 * das sollte dem Service auch egal sein, sonst macht das Interface
 * und die Kapselung dahinter keinen Sinn mehr.
 *
 * @author CGS
 * @since 1.0.0
 */
public class CheckoutService {
    private PaymentStrategy strategy;

    public CheckoutService(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void pay(double amount) {

        // mache die arbeit
        strategy.pay(amount);
    }
}
