package com.cooper.model.search;

import com.strutstool.collection.DataCollection;
import com.strutstool.search.EntityField;
import com.strutstool.search.EntityFieldType;
import com.strutstool.search.EntitySearchMap;

public class UsuarioSearchMap implements EntitySearchMap {

    private DataCollection<String, EntityField> fields;

    public DataCollection<String, EntityField> getFields() {
        fields = new DataCollection<String, EntityField>();
        // generator:fields
		fields.put("id", new EntityField("Id", "id", EntityFieldType.INT));
		fields.put("senha", new EntityField("Senha", "senha", EntityFieldType.STRING));
		fields.put("email", new EntityField("Email", "email", EntityFieldType.STRING));
		fields.put("status", new EntityField("Status", "status", EntityFieldType.STRING));
		fields.put("ultimoAcesso", new EntityField("UltimoAcesso", "ultimoAcesso", EntityFieldType.DATE));
		fields.put("criadoEm", new EntityField("CriadoEm", "criadoEm", EntityFieldType.DATE));


        return fields;
    }
    
}

