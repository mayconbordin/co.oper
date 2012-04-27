package com.cooper.model.search;

import com.strutstool.collection.DataCollection;
import com.strutstool.search.EntityField;
import com.strutstool.search.EntityFieldType;
import com.strutstool.search.EntitySearchMap;

public class GraosSearchMap implements EntitySearchMap {

    private DataCollection<String, EntityField> fields;

    public DataCollection<String, EntityField> getFields() {
        fields = new DataCollection<String, EntityField>();
        // generator:fields
		fields.put("id", new EntityField("Id", "id", EntityFieldType.INT));
		fields.put("romaneio", new EntityField("Romaneio", "romaneio", EntityFieldType.INT));
		fields.put("data", new EntityField("Data", "data", EntityFieldType.DATE));
		fields.put("descontos", new EntityField("Descontos", "descontos", EntityFieldType.DOUBLE));
		fields.put("descricao", new EntityField("Descricao", "descricao", EntityFieldType.STRING));
		fields.put("motoristaCaminhao", new EntityField("MotoristaCaminhao", "motoristaCaminhao", EntityFieldType.STRING));
		fields.put("pesoQtde", new EntityField("PesoQtde", "pesoQtde", EntityFieldType.DOUBLE));
		fields.put("placaCaminhao", new EntityField("PlacaCaminhao", "placaCaminhao", EntityFieldType.STRING));
		fields.put("produto", new EntityField("Produto", "produto", EntityFieldType.STRING));
		fields.put("status", new EntityField("Status", "status", EntityFieldType.STRING));


        return fields;
    }
    
}

