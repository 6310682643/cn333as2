# cn333as2
# QuizGame

เกมตอบคำถาม
Using App :

 ![App](app/src/main/res/drawable/cap3.jpg)

## clipสาธิตการทำงานของเกมตอบคำถาม
https://youtu.be/CgxVgcBEHNE

## Members

* Sireetorn Ontrakul 6310611048

* Chutirat Kaewchay 6310682643

## การทำงานของเกม

1. หน้าแรกจะเป็นหน้าเริ่มเกมหากต้องการที่จะเล่นสามารถกดที่ปุ่ม start game เพื่อเริ่มเล่นเกมได้
  
  ![Show Screen](app/src/main/res/drawable/home.png)

2. โปรแกรมจะทำการสุ่มคําถามมาหนึ่งคําถาม และแสดงตัวเลือก 4 ตัวเลือกโดยจะมีการจัดเรียงแบบสุ่มเช่นกัน โดยจะมีแทบด้านบนแสดงว่าตอบคำถามไปกี่คำถามแล้วจากกี่ทั้งหมดคำถามและแสดงscore ของผู้เล่นว่าตอบคำถามได้ถูกต้องไปแล้วกี่ข้อ โดยคำถาม 1 ข้อคือ 1 คะแนน

  ![Show Screen](app/src/main/res/drawable/q1.png)
  
3. เมื่อเล่นจนครบทุกคำถามก็จะเข้าสู่หน้า game over หน้านี้จะสรุปคะแนนให้ว่าผู้เล่นได้คะแนนทั้งหมดเท่าไร และจะมีทางเลือกให้ผู้เล่นเลือกว่าจะเล่นอีกครั้งหรือออกจากเกมโดยหากกดปุ่ม quit ก็จะออกจากเกมไป แต่หากกดปุ่ม play again ก็จะกลับไปสู่หน้าแรกของเกมจากนั้นก็สามารถกดปุ่ม start game เพื่อเล่นอีกครั้งได้ 

  ![Show Screen](app/src/main/res/drawable/over.png)
