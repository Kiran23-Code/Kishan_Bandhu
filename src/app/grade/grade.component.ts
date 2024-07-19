import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GradeResponse } from '../model/GradeResponse';
import { RestService } from '../service/rest.service';

@Component({
  selector: 'app-grade',
  templateUrl: './grade.component.html',
  styleUrls: ['./grade.component.scss']
})
export class GradeComponent implements OnInit {
  attachedFileName: string = '';
  base64SImage: string = '';
  grade: number = -1;
  price: number = -1;
  isGradeCalculating: boolean = false;
  constructor(private service: RestService, private router: Router) { }

  ngOnInit(): void {
  }
  fileInputChange(fileInputEvent: any) {
    console.log(fileInputEvent.target.files[0]);
    console.log("filename: " + fileInputEvent.target.files[0].name)
    this.attachedFileName = fileInputEvent.target.files[0].name;

    this.getFileToBase64(fileInputEvent.target.files[0]).then(data => {
      this.base64SImage = data.substring(data.indexOf(',') + 1)
      console.log("base64-attached-file: " + this.base64SImage)
    })
  }

  getFileToBase64(file: File) {
    return new Promise<string>((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result?.toString() || '');
      reader.onerror = error => reject(error);
    })
  }

  calculateGrade() {
    this.isGradeCalculating = true;
    console.log('isGradeCalculating : ' + this.isGradeCalculating);
    this.service.findGrade(this.base64SImage).subscribe((response: GradeResponse) => {
      this.isGradeCalculating = false;
      if (response && response.code === 200 && response.data) {
        this.grade = response.data.grade;
        this.price = response.data.price;
        console.log('grade: ' + response.data.grade);
      }
    });
  }

  goBack() {
    this.router.navigate(['/home'], { replaceUrl: true })
  }
}
