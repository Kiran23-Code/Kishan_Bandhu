package com.tce.kisanbandhu.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tce.kisanbandhu.grade.ProcessImage;
import com.tce.kisanbandhu.model.GenericResponse;
import com.tce.kisanbandhu.model.GradeModel;
import com.tce.kisanbandhu.model.Login;
import com.tce.kisanbandhu.utils.CommonUtils;

@RestController
@RequestMapping("/grade")
public class GradeController {

	@RequestMapping(value = "/process", method = RequestMethod.POST)
	public ResponseEntity<GenericResponse<GradeModel>> getGrade(@RequestBody String base6Str) {
		String path = "";
		try {
			path = CommonUtils.saveImage(base6Str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int grade = -1;
		if (path != null && path.length() > 0) {
			grade = ProcessImage.exec(path);
		}
		GenericResponse<GradeModel> obj = new GenericResponse<>();
		if (grade != -1) {
			
			obj.setCode(200);
			obj.setMessage("success");
			GradeModel model = new GradeModel();
			model.setGrade(grade);
			if(grade==1)
				model.setPrice(5550);
			else if(grade==2)
				model.setPrice(3950);
			else
				model.setPrice(2299);
			obj.setData(model);
		} else {
			obj.setCode(500);
			obj.setMessage("Something went wrong");
			obj.setData(null);
		}
		
		return ResponseEntity.ok(obj);
	}
	
}
