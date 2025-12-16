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

package at.cgsit.train.java.json_util_generic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;



// Hier ist die instanzbasierte Variante mit generischem Typ T
// JSON kennt also die Class information
// Class<T> hält die vollständig typisierte Klasseninformation,
// die Jackson benötigt, um den JSON-String korrekt zu einem Objekt des Typs T zu de-serialisieren.
public class JsonUtilsGen<T> {

    private static final ObjectMapper mapper = new ObjectMapper()
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

    private final Class<T> type;

    public JsonUtilsGen(Class<T> type) {
        this.type = type;
    }

    public String toJson(T obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public T fromJson(String json) {
        try {
            return mapper.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
