package it.cnr.isti.hpc.wikipedia.parser;

import de.tudarmstadt.ukp.wikipedia.parser.ParsedPage;
import de.tudarmstadt.ukp.wikipedia.parser.Template;
import de.tudarmstadt.ukp.wikipedia.parser.mediawiki.MediaWikiTemplateParser;
import de.tudarmstadt.ukp.wikipedia.parser.mediawiki.ResolvedTemplate;

/**
 * This TemplateParser shows the value of a parsed template
 * parameters, without any exception.
 */
public class ShowValue implements MediaWikiTemplateParser {
    public ResolvedTemplate parseTemplate(Template t, ParsedPage pp) {
        ResolvedTemplate result = new ResolvedTemplate( t );
        result.setPreParseReplacement( ResolvedTemplate.TEMPLATESPACER );

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
}
