package com.cooper.model.search;

import com.strutstool.collection.DataCollection;
import com.strutstool.search.EntityField;
import com.strutstool.search.EntityFieldType;
import com.strutstool.search.EntitySearchMap;

public class ProdutoNaLojaSearchMap implements EntitySearchMap {

    private DataCollection<String, EntityField> fields;

    public DataCollection<String, EntityField> getFields() {
        fields = new DataCollection<String, EntityField>();
        // generator:fields
		fields.put("id", new EntityField("Id", "id", EntityFieldType.INT));


        return fields;
    }
    
}

