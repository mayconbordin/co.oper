package com.cooper.model.search;

import com.strutstool.collection.DataCollection;
import com.strutstool.search.EntityField;
import com.strutstool.search.EntityFieldType;
import com.strutstool.search.EntitySearchMap;

public class MensagemSearchMap implements EntitySearchMap {

    private DataCollection<String, EntityField> fields;

    public DataCollection<String, EntityField> getFields() {
        fields = new DataCollection<String, EntityField>();
        // generator:fields
		fields.put("id", new EntityField("Id", "id", EntityFieldType.INT));
		fields.put("conteudo", new EntityField("Conteudo", "conteudo", EntityFieldType.STRING));
		fields.put("data", new EntityField("Data", "data", EntityFieldType.DATE));
		fields.put("lida", new EntityField("Lida", "lida", EntityFieldType.BOOLEAN));


        return fields;
    }
    
}

