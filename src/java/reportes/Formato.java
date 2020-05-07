/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
/**
 *
 * @author Osvaldo Vargas
 */
@RequiredArgsConstructor
public enum Formato {
    PDF("application/pdf"),
    XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    RTF("application/rtf"),
    HTML("text/html"),
    CSV("text/csv"),
    XML("application/xml");

    @Getter
    private final String mimeType;
}
