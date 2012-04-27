package com.cooper.model.repository.hibernate;

import com.cooper.model.entity.Produto;
import com.cooper.model.repository.ProdutoRepository;
import com.strutstool.hibernate.repository.LookupRepositoryHibernate;
import com.strutstool.repository.RepositoryException;
import java.util.Collections;
import java.util.List;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.util.Version;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

public class ProdutoRepositoryHibernate extends LookupRepositoryHibernate<Produto, Integer> implements ProdutoRepository {
    public ProdutoRepositoryHibernate() {
        super(Produto.class);
    }

    public List<Produto> searchProdutos(String searchString) throws RepositoryException {
        Transaction t = null;
        try {
            FullTextSession fullTextSession = Search.getFullTextSession(getSession());
            t = fullTextSession.beginTransaction();

            //searchString = "*"+searchString+"*";

            /*
            org.apache.lucene.search.Query query = MultiFieldQueryParser.parse(
                    Version.LUCENE_31,
                    new String[] { searchString, searchString, searchString },
                    new String[] { "codigo", "descricao", "preco" },
                    new StandardAnalyzer(Version.LUCENE_31, Collections.emptySet()));
             *
             */

            QueryBuilder queryBuilder = fullTextSession.getSearchFactory()
                .buildQueryBuilder()
                .forEntity( getPersistentClass() )
                .get();

            org.apache.lucene.search.Query query = queryBuilder
                .keyword()
                    .wildcard()
                        .onField("codigo").andField("descricao").andField("preco")
                .matching("*"+searchString+"*")
                .createQuery();

            FullTextQuery fullTextQuery =
                fullTextSession.createFullTextQuery(query, getPersistentClass());

            List result = fullTextQuery.list();
            t.commit();
            return result;
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }
}
