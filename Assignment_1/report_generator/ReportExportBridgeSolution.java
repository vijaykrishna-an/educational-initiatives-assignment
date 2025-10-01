package Day1.Assign1.Structural.Bridge;

interface Format {
    public void export();
}

class PDF_Format implements Format {
    public void export() {
        System.out.println(" -> PDF");
    }
}

class Excel_Format implements Format {
    public void export() {
        System.out.println(" -> Excel");
    }
}

abstract class Report {
    protected Format exportFormat;
    Report(Format exportFormat) {
        this.exportFormat = exportFormat;
    }
    public abstract void generate();
}

class MarksReport extends Report {
    MarksReport(Format exportFormat) {
        super(exportFormat);
    }
    public void generate() {
        System.out.print("MarksReport");
        exportFormat.export();
    }
}

class AttendanceReport extends Report {
    AttendanceReport(Format exportFormat) {
        super(exportFormat);
    }
    public void generate() {
        System.out.print("AttendanceReport");
        exportFormat.export();
    }
}

public class ReportExportBridgeSolution {
    public static void main(String[] args) {
        Report report = new MarksReport(new PDF_Format());
        report.generate();
        report = new MarksReport(new Excel_Format());
        report.generate();
        report = new AttendanceReport(new PDF_Format());
        report.generate();
        report = new AttendanceReport(new Excel_Format());
        report.generate();
    }
}