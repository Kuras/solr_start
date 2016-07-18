package pl.sii.solr;

import org.apache.solr.client.solrj.impl.HttpSolrClient;

import java.util.Objects;

/**
 * Created by pkura on 2016-07-12.
 */

public class SiiSolrServer {
    private static HttpSolrClient solrClientWsi;
    private static HttpSolrClient solrClientDemo;

    public static HttpSolrClient getSolrClient(String core) {

        if (core.contains( "demo" )){
            if ( Objects.isNull( solrClientDemo ) ) {
                solrClientDemo = new HttpSolrClient( "http://localhost:8983/solr/".concat( core ) );
            }
            return solrClientDemo;
        } else if (core.contains( "wsi" )){
            if ( Objects.isNull( solrClientWsi ) ) {
                solrClientWsi = new HttpSolrClient( "http://localhost:8983/solr/".concat( core ) );
            }
            return solrClientWsi;
        }
        return null;
    }
}
