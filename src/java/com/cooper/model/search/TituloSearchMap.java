package com.cooper.model.search;

import com.strutstool.collection.DataCollection;
import com.strutstool.search.EntityField;
import com.strutstool.search.EntityFieldType;
import com.strutstool.search.EntitySearchMap;

public class TituloSearchMap implements EntitySearchMap {

    private DataCollection<String, EntityField> fields;

    public DataCollection<String, EntityField> getFields() {
        fields = new DataCollection<String, EntityField>();
        // generator:fields
		fields.put("id", new EntityField("Id", "id", EntityFieldType.INT));
		fields.put("dataCriacao", new EntityField("DataCriacao", "dataCriacao", EntityFieldType.DATE));
		fields.put("dataVencimento", new EntityField("DataVencimento", "dataVencimento", EntityFieldType.DATE));
		fields.put("status", new EntityField("Status", "status", EntityFieldType.STRING));
		fields.put("tipo", new EntityField("Tipo", "tipo", EntityFieldType.STRING));
		fields.put("valor", new EntityField("Valor", "valor", EntityFieldType.DOUBLE));


        return fields;
    }
    
}

