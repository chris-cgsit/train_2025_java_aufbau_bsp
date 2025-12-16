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

package at.cgsit.train.java.patterns.templatem;

public class CsvImporter extends DataImporter {

    @Override
    protected void readData() {
        System.out.println("Read CSV");
    }

    @Override
    protected void processData() {
        System.out.println("Process CSV data");
    }
}
