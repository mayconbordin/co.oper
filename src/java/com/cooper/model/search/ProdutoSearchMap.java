package com.cooper.model.search;

import com.strutstool.collection.DataCollection;
import com.strutstool.search.EntityField;
import com.strutstool.search.EntityFieldType;
import com.strutstool.search.EntitySearchMap;

public class ProdutoSearchMap implements EntitySearchMap {

    private DataCollection<String, EntityField> fields;

    public DataCollection<String, EntityField> getFields() {
        fields = new DataCollection<String, EntityField>();
        // generator:fields
		fields.put("id", new EntityField("Id", "id", EntityFieldType.INT));
		fields.put("codigo", new EntityField("Codigo", "codigo", EntityFieldType.INT));
		fields.put("descricao", new EntityField("Descricao", "descricao", EntityFieldType.STRING));
		fields.put("preco", new EntityField("Preco", "preco", EntityFieldType.DOUBLE));


        return fields;
    }
    
}

