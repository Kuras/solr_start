package pl.sii.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by pkura on 2016-07-11.
 */
public class Main {

    public static void main( String[] args ) throws IOException, SolrServerException {
        System.out.println("Solr");
        HttpSolrClient server = SiiSolrServer.getSolrClient();

        SolrInputDocument doc1 = new SolrInputDocument();
        doc1.addField( "id", "id1", 1.0f );
        doc1.addField( "name", "doc1", 1.0f );
        doc1.addField( "price", 10 );

        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        docs.add( doc1 );

        server.add( docs );
        server.commit();
        server.rollback();

        // Search
        SolrQuery query = new SolrQuery();
        query.setQuery( "id:" + "id1" );
        QueryResponse response = server.query( query );
        SolrDocumentList resDocs = response.getResults();

        // Update
        SolrDocument doc = resDocs.get( 0 );
        doc1.setField( "id", doc.getFieldValue("id").toString(), 1.0f );
        doc1.setField( "price", 111 );
        server.add( doc1 );
        server.commit( true, true, true );
        server.close();
    }
}
