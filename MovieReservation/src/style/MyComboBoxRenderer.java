package style;
 
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
 
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
 
public class MyComboBoxRenderer extends JLabel implements ListCellRenderer {
	int size;
    public MyComboBoxRenderer(int size) {
    	this.size = size;
    	Font font=new Font("HY엽서L", Font.BOLD, size);
        setOpaque(true);
        setFont(font);
        setForeground(new Color(170, 18, 18));
		setBackground(Color.WHITE);
    }
     
    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.toString());
        Font font=new Font("HY엽서L", Font.BOLD, size);//글꼴,크기
		setFont(font);
		if(!isSelected) {
			if(index%2==0) {
				setForeground(new Color(170, 18, 18));
				setBackground(Color.WHITE);
			}else {
				setForeground(new Color(13, 113, 62));
				setBackground(Color.WHITE);
			}
		}
		if(isSelected) {
			if(index%2==0) {
				setBackground(new Color(170, 18, 18));
				setForeground(Color.WHITE);
			}else {
				setBackground(new Color(13, 113, 62));
				setForeground(Color.WHITE);
			}
		}
        return this;
    }
 
}
