package manageMent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import style.Style;

public class DeleteDialog extends JDialog {
	JButton btnOk = new JButton("확인");
	JButton btnCancel = new JButton("취소");
	UpdateDelPan uPan;

	public DeleteDialog(UpdateDelPan uPan, Management frame) {
		super(frame, true);
		this.uPan =  uPan;
		JLabel lbl = new JLabel("정말로 삭제하시겠습니까?",JLabel.CENTER);
		JPanel pan = new JPanel();
		pan.add(btnOk);
		pan.add(btnCancel);
		add(lbl,"Center");
		add(pan,"South");
		btnOk.addActionListener(btnHandler);
		btnCancel.addActionListener(btnHandler);
		
		pan.setBackground(new Color(13, 113, 62));
		new Style(lbl, 15);
		new Style(btnOk, 15, new Color(238, 28, 37), Color.WHITE);
		new Style(btnCancel, 15, new Color(51, 51, 51), Color.WHITE);
		new Style(300, 200, this);
		setVisible(true);
	}
	
	ActionListener btnHandler = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Object eBut = e.getSource();
			
			if(eBut == btnOk) {
				uPan.DeleteSQL();
				DeleteDialog.this.dispose();
				uPan.sPan.execSQL();
				uPan.sPan.setTable();
				uPan.init();
			}else{
				DeleteDialog.this.dispose();
				uPan.init();
				return;
			}
		}
	};
}
