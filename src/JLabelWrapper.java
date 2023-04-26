import javax.swing.JLabel;
import javax.swing.JPanel;

public class JLabelWrapper {

    JLabel childTime;
    JLabel childLabel;
    JPanel applicationPanel;
    JLabel backgroundPanel;



    public JLabelWrapper(JLabel childTime, JLabel childLabel, JPanel applicationPanel, JLabel backgroundPanel
                         ) {
        this.childLabel = childLabel;
        this.applicationPanel = applicationPanel;
        this.backgroundPanel = backgroundPanel;
        this.childTime = childTime;
    }
}