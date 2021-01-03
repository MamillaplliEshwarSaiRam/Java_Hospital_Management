package Hospitalmanagement;

import java.io.IOException;

public interface Patient {
	void store();
	void symptom();
	void act() throws Exception;
	void predictor();
	double type1_diabetes();
	double type2_diabetes();
	double heart_disease();
	double obesity();
	void tips();
	void medication();
	void action_taker() throws Exception;
	void show();
	void search(String n,String p);
	int validate(String n,String p);
	void delete_record(String n,String p) throws Exception;
}