import tkinter as tk
import docx
import tkinter.ttk as ttk
from tkinter import messagebox
import random as rnd
from datetime import date
from tkinter.filedialog import askopenfilename
from mailmerge import MailMerge
from operator import itemgetter

MainWindow = tk.Tk()
MainWindow.attributes("-fullscreen", True)
MainWindow["bg"] = "#FFDEAD"
StringsNum_Info = tk.StringVar()
FIO_Info = tk.StringVar()
Org_Info = tk.StringVar()
Num_Info = tk.StringVar()
ChosenFile = ""
word_file_name = ""
data = []
sortFIOs_Reverse = False
sortDolz_Reverse = False
sortDates_Reverse = False
sortFinances_Reverse = False


RandomFIOs = ['Анисимов Соломон Авксентьевич', 'Кононов Виктор Валерьевич', 'Куликова Злата Георгиевна',
              'Горбачев Рудольф Арсениевич', 'Маркова Станислава Ивановна', 'Веселова Роза Ивановна',
              'Тимофеев Севастьян Ярославович', 'Семёнова Христина Тихоновна', 'Тарасов Леонтий Юрьевич',
              'Жданов Иван Данилович', 'Михеев Бронислав Алексеевич', 'Крылова Эдда Аркадьевна',
              'Михеев Бронислав Алексеевич', 'Крылова Эдда Аркадьевна', 'Комаров Тарас Куприянович',
              'Бобров Роман Данилович', 'Крылов Иван Парфениевич', 'Щукина Аурелиа Федосеевна',
              'Бондарев Сергей Даниилович', 'Осипова Ева Егоровна', 'Сазонов Демид Иванович', 'Морозов Дмитрий Михайлович',
              'Быков Степан Михайлович', 'Воробьева Анна Максимовна', 'Бирюкова София Львовна',
              'Никитин Семён Владимирович', 'Сергеев Захар Вячеславович', 'Соколов Тимур Иванович',
              'Емельянова Арина Львовна', 'Москвина Анна Матвеевна', 'Зотов Савва Арсентьевич', 'Сергеев Андрей Сергеевич',
              'Лобанов Александр Никитич', 'Смирнова Малика Адамовна', 'Мельников Максим Кириллович', 'Маслова Мария Георгиевна',
              'Карпов Михаил Матвеевич', 'Прохорова Ясмина Алиевна', 'Иванова Екатерина Егоровна', 'Тарасов Лука Алексеевич',
              'Морозова Василиса Святославовна', 'Давыдов Никита Михайлович', 'Гончарова Анастасия Никитична']


RandomDates = ['02.05.1984', '11.05.1987', '19.08.1988', '12.09.1988', '26.02.1990', '26.01.1992', '02.09.1995',
               '09.10.1995', '12.11.1997', '09.01.1998', '25.02.2001', '01.10.1983', '27.09.1984', '05.05.1986',
               '06.04.1988', '10.07.1992', '10.09.1996', '21.10.1958', '13.09.1962', '13.04.1963', '27.05.1983',
               '13.12.2007', '11.12.1959', '13.02.1984', '19.12.1988', '19.07.1993', '24.06.1997', '23.01.1956',
               '14.03.1972', '07.09.1982', '08.12.2000', '18.06.2010', '17.03.1975', '16.01.1976', '09.12.1984',
               '10.02.1992', '27.11.2005', '17.04.1958', '22.08.1964', '12.08.1974', '11.05.1977', '10.10.1987']

RandomSalaries = ['730.56', '87.98', '635.32', '206.65', '250.12', '3000.54', '1350.54', '400.33']

RandomDolz = ['Директор', 'Менеджер', 'Инженер', 'Рабочий']



headings = ('ФИО', 'Должность', 'Дата Рождения', 'Зарплата')

strings_amount_num = 0


def GenerateBtn_Clicked():
    strings_amount_Str = StringsNum_Info.get()
    if strings_amount_Str:
        try:
            strings_amount_num = int(strings_amount_Str)
            if strings_amount_num <= 0:
                raise Exception("")
        except ValueError:
            messagebox.showerror("Ошибка ввода количества строк", "Количество строк должно быть числом больше 0!")
            return
        strings_amount_num = int(strings_amount_Str)

        for row in table.get_children():
                table.delete(row)

        global data
        data = []
        for i in range(strings_amount_num):
            one_str_data = [rnd.choice(RandomFIOs), rnd.choice(RandomDolz), rnd.choice(RandomDates), rnd.choice(RandomSalaries)]
            data.append(one_str_data)
        for str in data:
            table.insert('', tk.END, values=tuple(str))

    else:
        messagebox.showerror("Ошибка ввода количества строк", "Надо ввести кол-во строк!")




def SortFIOsBtn_Clicked():
    global data
    global sortFIOs_Reverse
    global sortDolz_Reverse
    global sortDates_Reverse
    global sortFinances_Reverse
    if not sortFIOs_Reverse:
        data.sort()
        sortFIOs_Reverse = True
    else:
        return
    for row in table.get_children():
        table.delete(row)
    for str in data:
        table.insert('', tk.END, values=tuple(str))
    sortDolz_Reverse = False
    sortDates_Reverse = False
    sortFinances_Reverse = False

def SortByDolz(one_str_data):
    return one_str_data[1]

def SortDolzBtn_Clicked():
    global data
    global sortFIOs_Reverse
    global sortDolz_Reverse
    global sortDates_Reverse
    global sortFinances_Reverse
    if not sortDolz_Reverse:
        data.sort(key=SortByDolz)
        sortDolz_Reverse = True
    else:
        return
    for row in table.get_children():
        table.delete(row)
    for str in data:
        table.insert('', tk.END, values=tuple(str))
    sortFIOs_Reverse = False
    sortDates_Reverse = False
    sortFinances_Reverse = False


def SortByDates(one_str_data):
    curr_date = one_str_data[2].split('.')[::-1]
    return date(int(curr_date[0]), int(curr_date[1]), int(curr_date[2]))

def SortDatesBtn_Clicked():
    global data
    global sortFIOs_Reverse
    global sortDolz_Reverse
    global sortDates_Reverse
    global sortFinances_Reverse
    if not sortDates_Reverse:
        data.sort(key=SortByDates)
        sortDates_Reverse = True
    else:
        return
    for row in table.get_children():
        table.delete(row)
    for str in data:
        table.insert('', tk.END, values=tuple(str))
    sortFIOs_Reverse = False
    sortDolz_Reverse = False
    sortFinances_Reverse = False

def SortBySalaries(one_str_data):
    return float(one_str_data[3])

def SortSalariesBtn_Clicked():
    global data
    global sortFIOs_Reverse
    global sortDolz_Reverse
    global sortDates_Reverse
    global sortFinances_Reverse
    if not sortFinances_Reverse:
        data.sort(key=SortBySalaries)
        sortFinances_Reverse = True
    else:
        return
    for row in table.get_children():
        table.delete(row)
    for str in data:
        table.insert('', tk.END, values=tuple(str))
    sortFIOs_Reverse = False
    sortDolz_Reverse = False
    sortDates_Reverse = False


def ChooseTemplateBtn_Clicked():
    global word_file_name
    global ChosenFile
    word_file_name = askopenfilename()
    if word_file_name.endswith('docx'):
        Lbl_ChosenFileInfo.config(text="Выбранный файл шаблона: " + word_file_name)
    else:
        messagebox.showerror("Ошибка выбора файла шаблона", "Вы нажали отмену либо выбрали какой-то файл, но это не файл шаблона!")



def CheckOrgName(str):
    if str == "":
        messagebox.showerror("Ошибка ввода организации", "Имя организации не может быть пустым!")
    else:
        return True
    return False

def CheckFIO(str):
    if str == "":
        messagebox.showerror("Ошибка ввода ФИО", "ФИО не может быть пустым!")
    else:
        if str.count(' ') != 2:
            messagebox.showerror("Ошибка ввода ФИО", "ФИО должно состоять из трёх слов!")
        else:
            str_without_spaces = str.replace(' ', '')
            if str_without_spaces.isalpha():
                uppers_amount = [l for l in str if l.isupper()]
                words = str.split(' ')
                if len(uppers_amount) == 3 and words[0][0].isupper() and words[1][0].isupper() and words[2][0].isupper():
                    return True
                else:
                    messagebox.showerror("Ошибка ввода ФИО",
                                     "Каждое из трёх слов ФИО должно начинаться с большой буквы!")
            else:
                messagebox.showerror("Ошибка ввода ФИО",
                                     "В ФИО могут быть использованы только буквы!")
    return False

def CheckNum(str):
    if str == "":
        messagebox.showerror("Ошибка ввода мобильного телефона главы команды", "Мобильный телефон главы команды не может быть пустым!")
    else:
        if str.isdigit():
            return True
        else:
            messagebox.showerror("Ошибка ввода ",
                                 "Номер введён некорректно!")
    return False



def CreateDocBtn_Clicked():
    global data
    global word_file_name
    global Org_Info
    global FIO_Info
    global Num_Info
    if len(data) == 0:
        messagebox.showerror("Ошибка",
                             "Сначала сгенерируйте таблицу")
        return
    if word_file_name.endswith('docx'):
        if CheckOrgName(Org_Info.get()) and CheckFIO(FIO_Info.get()) and CheckNum(Num_Info.get()):

            template_doc = MailMerge(word_file_name)

            Fio = FIO_Info.get()
            Num = Num_Info.get()
            Name = Org_Info.get()

            template_doc.merge(
                name=Name,
                FIO=Fio,
                num=Num,
            )


            data_word_list = []


            for row in data:
                data_word_list.append(
                {'emp_fio': row[0],
                'dolznosc': row[1],
                'birth': row[2],
                'salary': row[3],
                },)

            template_doc.merge_rows('emp_fio', data_word_list)

            template_doc.write('Result.docx')

            template_doc.close()

            new_doc = docx.Document('Result.docx')

            new_doc.save('Result.docx')

            import os
            os.system('start Result.docx')
    else:
        messagebox.showerror("Ошибка выбора файла шаблона", "Вы не выбрали файл шаблона!")



label_name = tk.Label(MainWindow, text="Ракоть Валентин, 3 курс, 12 группа, 2021 год",
                            font=("Arial Bold", 18), bg="#FFDEAD")
label_name.place(relx=0.21, rely=0.02, anchor="center")

label_strnum = tk.Label(MainWindow, text="Количество строк таблицы:", font=("Arial Bold", 28), bg="#FFDEAD")
label_strnum.place(relx=0.19, rely=0.07, anchor="center")

text_strnum = tk.Entry(MainWindow, width=10, bd=5, font=("Arial Bold", 28), textvariable=StringsNum_Info)
text_strnum.place(relx=0.19, rely=0.15, anchor="nw")

btn_gen = tk.Button(MainWindow, text="Сгенерировать таблицу", font=("Arial Bold", 28), bd=10,
                        background="#CC9B00", command=GenerateBtn_Clicked,
                          width=20)
btn_gen.place(relx=0.19, rely=0.27, anchor="center")

sort_name = tk.Button(MainWindow, text="Сортировать по ФИО", font=("Arial Bold", 28), bd=10,
                        background="#CC9B00", command=SortFIOsBtn_Clicked,
                         width=24)
sort_name.place(relx=0.19, rely=0.43, anchor="center")

sort_dolz = tk.Button(MainWindow, text="Сортировать по должности", font=("Arial Bold", 28), bd=10,
                        background="#CC9B00", command=SortDolzBtn_Clicked, width=24)
sort_dolz.place(relx=0.19, rely=0.54, anchor="center")

sort_date = tk.Button(MainWindow, text="Сортировать по дате", font=("Arial Bold", 28), bd=10,
                        background="#CC9B00", command=SortDatesBtn_Clicked,
                          width=24)
sort_date.place(relx=0.19, rely=0.65, anchor="center")

sort_sal = tk.Button(MainWindow, text="Сортировать по зарплате", font=("Arial Bold", 28), bd=10,
                        background="#CC9B00", command=SortSalariesBtn_Clicked,
                             width=24)
sort_sal.place(relx=0.19, rely=0.76, anchor="center")

btn_ex = tk.Button(MainWindow, text="Выход", font=("Arial Bold", 28), bd=10,
                        background="#CC9B00", command=MainWindow.quit, width=20)
btn_ex.place(relx=0.19, rely=0.925, anchor="center")

project_name = tk.Label(MainWindow, text="Участники конкурса организаций", font=("Arial Bold", 36), bg="#FFDEAD")
project_name.place(relx=0.7, rely=0.04, anchor="center")

org_name = tk.Label(MainWindow, text="Название организации:", font=("Arial Bold", 18), bg="#FFDEAD")
org_name.place(relx=0.5, rely=0.12, anchor="center")

TxtEdit_EnterOrgName = tk.Entry(MainWindow, width=34, bd=5, font=("Arial Bold", 20), textvariable=Org_Info)
TxtEdit_EnterOrgName.place(relx=0.82, rely=0.12, anchor="center")

Lbl_FIO = tk.Label(MainWindow, text="ФИО:", font=("Arial Bold", 18), bg="#FFDEAD")
Lbl_FIO.place(relx=0.5655, rely=0.2, anchor="center")

TxtEdit_EnterFIO = tk.Entry(MainWindow, width=34, bd=5, font=("Arial Bold", 20), textvariable=FIO_Info)
TxtEdit_EnterFIO.place(relx=0.82, rely=0.2, anchor="center")

Lbl_Num = tk.Label(MainWindow, text="Номер организации:", font=("Arial Bold", 18), bg="#FFDEAD")
Lbl_Num.place(relx=0.508, rely=0.28, anchor="center")

TxtEdit_EnterNum = tk.Entry(MainWindow, width=34, bd=5, font=("Arial Bold", 20), textvariable=Num_Info)
TxtEdit_EnterNum.place(relx=0.82, rely=0.28, anchor="center")



table = ttk.Treeview(MainWindow, height=15, show="headings", selectmode="browse")
table["columns"] = headings
#table["displaycolumns"] = headings
table.heading(headings[0], text=headings[0], anchor=tk.E)
table.column(headings[0], width=400, anchor=tk.E)
table.heading(headings[1], text=headings[1], anchor=tk.E)
table.column(headings[1], width=150, anchor=tk.E)
table.heading(headings[2], text=headings[2], anchor=tk.CENTER)
table.column(headings[2], width=180, anchor=tk.CENTER)
table.heading(headings[3], text=headings[3], anchor=tk.W)
table.column(headings[3], width=180, anchor=tk.W)
#table.column('#' + str(0), minwidth=700, stretch=0)
#table.column('#' + str(1), minwidth=200, stretch=0)
#table.column('#' + str(2), minwidth=300, stretch=0)
#table.column('#' + str(3), minwidth=400, stretch=0)

#table_scroll = tk.Scrollbar(MainWindow, command=table.yview)
#table.configure(yscrollcommand=table_scroll.set)
#table_scroll.place(relx=0.975, rely=0.43, height=328, width=25)
table.place(relx=0.4, rely=0.43)

Lbl_ChosenFileInfo = tk.Label(MainWindow, text="Выбранный файл шаблона: (пока не выбран)", font=("Arial Bold", 18), bg="#FFDEAD")
Lbl_ChosenFileInfo.place(relx=0.5, rely=0.84, anchor="c")

Btn_ChooseTemplate = tk.Button(MainWindow, text="Выбрать шаблон", font=("Arial Bold", 28), bd=15,
                        background="#CC9B00", command=ChooseTemplateBtn_Clicked,
                                width=19)
Btn_ChooseTemplate.place(relx=0.545, rely=0.925, anchor="c")

Btn_ChooseTemplate = tk.Button(MainWindow, text="Создать документ", font=("Arial Bold", 28), bd=15,
                        background="#CC9B00", command=CreateDocBtn_Clicked,
                                width=19)
Btn_ChooseTemplate.place(relx=0.845, rely=0.925, anchor="c")


MainWindow.mainloop()