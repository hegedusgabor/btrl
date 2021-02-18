package ro.btrl.demo.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Component
public class PdfGenaratorUtil {
    @Autowired
    private TemplateEngine templateEngine;
    
    public ByteArrayInputStream createPdf(String templateName, Map<String, Object> map) throws Exception {
        Context ctx = new Context();
        Iterator<?> itMap = map.entrySet().iterator();
        while (itMap.hasNext()) {
            Entry<?, ?> pair =  (Entry<?, ?>) itMap.next();
            ctx.setVariable(pair.getKey().toString(), pair.getValue());
        }

        String processedHtml = templateEngine.process(templateName, ctx);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
     
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();
            renderer.createPDF(os, false);
            renderer.finishPDF();

        }
        finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) { }
            }
        }
		return new ByteArrayInputStream(os.toByteArray());
        
    }
}