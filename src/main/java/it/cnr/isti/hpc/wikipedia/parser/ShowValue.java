package it.cnr.isti.hpc.wikipedia.parser;

import de.tudarmstadt.ukp.wikipedia.parser.ParsedPage;
import de.tudarmstadt.ukp.wikipedia.parser.Template;
import de.tudarmstadt.ukp.wikipedia.parser.mediawiki.MediaWikiTemplateParser;
import de.tudarmstadt.ukp.wikipedia.parser.mediawiki.ResolvedTemplate;

import java.util.List;

/**
 * This TemplateParser shows the value of a parsed template
 * parameters, without any exception.
 */
public class ShowValue implements MediaWikiTemplateParser {
    private List<String> deleteTemplates;

    public ResolvedTemplate parseTemplate(Template t, ParsedPage pp) {
        ResolvedTemplate result = new ResolvedTemplate( t );
        result.setPreParseReplacement( ResolvedTemplate.TEMPLATESPACER );

        // dont show template if in deleteTemplates
        for( String s: deleteTemplates ){
            if( s.equals(t.getName()) ){
                result.setPostParseReplacement( "" );
                result.setParsedObject( null );
                return result;
            }
        }

        StringBuilder sb = new StringBuilder();

        // fetch value if available
        if (!t.getParameters().isEmpty()) {
            sb.append( t.getParameters().get(0) );
        }

        result.setPostParseReplacement( sb.toString() );
        result.setParsedObject( t );
        return result;
    }

    public String configurationInfo(){
        return "shows the Template value or empty string";
    }

    @Override
    public void setDeleteTemplates(List<String> deleteTemplates) {
        this.deleteTemplates = deleteTemplates;
    }
}
