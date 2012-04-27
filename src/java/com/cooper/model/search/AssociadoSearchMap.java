package com.cooper.model.search;

import com.strutstool.collection.DataCollection;
import com.strutstool.search.EntityField;
import com.strutstool.search.EntityFieldType;
import com.strutstool.search.EntitySearchMap;

public class AssociadoSearchMap implements EntitySearchMap {

    private DataCollection<String, EntityField> fields;

    public DataCollection<String, EntityField> getFields() {
        fields = new DataCollection<String, EntityField>();
        // generator:fields
		fields.put("id", new EntityField("Id", "id", EntityFieldType.INT));
		fields.put("codigo", new EntityField("Codigo", "codigo", EntityFieldType.STRING));
		fields.put("nome", new EntityField("Nome", "nome", EntityFieldType.STRING));
		fields.put("cpfcnpj", new EntityField("Cpfcnpj", "cpfcnpj", EntityFieldType.STRING));
		fields.put("dataCadastro", new EntityField("DataCadastro", "dataCadastro", EntityFieldType.DATE));


        return fields;
    }
    
}

