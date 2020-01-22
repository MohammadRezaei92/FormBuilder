package ir.co.yalda.formbuilder.utility

/**
 * Created by Rezaei_M on 3/6/2018.
 */
object Json {
    const val json = "{\n" +
            "  \"MainFormCode\": 25,\n" +
            "  \"MainFormVersion\": \"25.1\",\n" +
            "  \"MainFormTitle\": \"مشاهده قبوض صادر شده\",\n" +
            "  \"MainFormEnglishName\": \"MainFrmViewBill\",\n" +
            "  \"MainFormSubmitUrl\": null,\n" +
            "  \"MainFormOutputType\": \"None|Table|Message|Graph|PlainJson\",\n" +
            "  \"FormsViewMode\": \"Tabular|GroupBox\",\n" +
            "  \"Forms\": [\n" +
            "    {\n" +
            "      \"FormCode\": 12,\n" +
            "      \"FormVersion\": \"12.1\",\n" +
            "      \"FormTitle\": \"مشاهده قبوض صادر شده دوره اي\",\n" +
            "      \"FormEnglishName\": \"FrmViewBillPerPeriod\",\n" +
            "      \"SubmitUrl\": \"http://192.168.127.49/api/customer/viewBillPerPeriod\",\n" +
            "      \"OutputType\": \"None|Table|Message|Graph|PlainJson\",\n" +
            "      \"FormCondition\": \"(NumYear!=null || DropYear!=null) && (NumYear>=1390 && NumYear<=1397)\",\n" +
            "      \"Elements\": [\n" +
            "        {\n" +
            "          \"Type\": \"Numeric\",\n" +
            "          \"Name\": \"NumYear\",\n" +
            "          \"Title\": \"سال\",\n" +
            "          \"IsUsedAsInputParam\": true,\n" +
            "          \"DefaultValue\": 1396,\n" +
            "          \"Validation\": {\n" +
            "            \"IsRequired\": false\n" +
            "          },\n" +
            "          \"Style\": {\n" +
            "            \"Dimension\": {\n" +
            "              \"PhoneVerticalUnit\": \"0:6\",\n" +
            "              \"PhoneHorizontalUnit\": \"0:3\",\n" +
            "              \"TabletVerticalUnit\": \"0:3\",\n" +
            "              \"TabletHorizontalUnit\": \"0:2\"\n" +
            "            },\n" +
            "            \"Type\": \"YaldaDefault|Material\",\n" +
            "            \"BackgroundColor\": \"#abff44\",\n" +
            "            \"FontColor\": \"black\",\n" +
            "            \"Font\": \"Tahoma\",\n" +
            "            \"FontSize\": \"12px\",\n" +
            "            \"IconStyle\": \"fa-calendar\",\n" +
            "            \"Height\": \"40px\",\n" +
            "            \"TextAlign\": \"center\",\n" +
            "            \"Direction\": \"LTR\"\n" +
            "          }\n" +
            "        },\n" +
            "        {\n" +
            "          \"Type\": \"Dropdown\",\n" +
            "          \"Name\": \"DropYear\",\n" +
            "          \"Title\": \"سال\",\n" +
            "          \"IsUsedAsInputParam\": true,\n" +
            "          \"DefaultValue\": 1396,\n" +
            "          \"List\": [\n" +
            "            {\n" +
            "              \"Text\": 1396,\n" +
            "              \"Value\": 1396\n" +
            "            },\n" +
            "            {\n" +
            "              \"Text\": 1395,\n" +
            "              \"Value\": 1395\n" +
            "            },\n" +
            "            {\n" +
            "              \"Text\": 1394,\n" +
            "              \"Value\": 1394\n" +
            "            }\n" +
            "          ],\n" +
            "          \"Validation\": {\n" +
            "            \"IsRequired\": false\n" +
            "          },\n" +
            "          \"GetDataUrl\": \"http://192.168.127.49/api/pub/getPeriodYearByBillID\",\n" +
            "          \"Style\": {\n" +
            "            \"Dimension\": {\n" +
            "              \"PhoneVerticalUnit\": \"0:6\",\n" +
            "              \"PhoneHorizontalUnit\": \"0:3\",\n" +
            "              \"TabletVerticalUnit\": \"0:3\",\n" +
            "              \"TabletHorizontalUnit\": \"0:2\"\n" +
            "            },\n" +
            "            \"Type\": \"YaldaDefault|Material\",\n" +
            "            \"BackgroundColor\": \"#abff44\",\n" +
            "            \"FontColor\": \"black\",\n" +
            "            \"Font\": \"Tahoma\",\n" +
            "            \"FontSize\": \"12px\",\n" +
            "            \"IconStyle\": \"fa-calendar\",\n" +
            "            \"Height\": \"40px\",\n" +
            "            \"TextAlign\": \"center\",\n" +
            "            \"Direction\": \"LTR\"\n" +
            "          }\n" +
            "        },\n" +
            "        {\n" +
            "          \"Type\": \"Dropdown\",\n" +
            "          \"Name\": \"DropPeriod\",\n" +
            "          \"Title\": \"دوره\",\n" +
            "          \"IsUsedAsInputParam\": true,\n" +
            "          \"DefaultValue\": 6,\n" +
            "          \"List\": [\n" +
            "            {\n" +
            "              \"Text\": \"دوره ششم\",\n" +
            "              \"Value\": 6\n" +
            "            },\n" +
            "            {\n" +
            "              \"Text\": \"دوره پنجم\",\n" +
            "              \"Value\": 5\n" +
            "            },\n" +
            "            {\n" +
            "              \"Text\": \"دوره چهارم\",\n" +
            "              \"Value\": 4\n" +
            "            },\n" +
            "            {\n" +
            "              \"Text\": \"دوره سوم\",\n" +
            "              \"Value\": 3\n" +
            "            },\n" +
            "            {\n" +
            "              \"Text\": \"دوره دوم\",\n" +
            "              \"Value\": 2\n" +
            "            },\n" +
            "            {\n" +
            "              \"Text\": \"دوره اول\",\n" +
            "              \"Value\": 1\n" +
            "            }\n" +
            "          ],\n" +
            "          \"Validation\": {\n" +
            "            \"IsRequired\": true\n" +
            "          },\n" +
            "          \"Style\": {\n" +
            "            \"Dimension\": {\n" +
            "              \"PhoneVerticalUnit\": \"0:6\",\n" +
            "              \"PhoneHorizontalUnit\": \"3:3\",\n" +
            "              \"TabletVerticalUnit\": \"3:3\",\n" +
            "              \"TabletHorizontalUnit\": \"4:2\"\n" +
            "            },\n" +
            "            \"Type\": \"YaldaDefault|Material\",\n" +
            "            \"BackgroundColor\": \"#abff44\",\n" +
            "            \"FontColor\": \"black\",\n" +
            "            \"Font\": \"Tahoma\",\n" +
            "            \"FontSize\": \"12px\",\n" +
            "            \"IconStyle\": \"fa-calendar\",\n" +
            "            \"Height\": \"40px\",\n" +
            "            \"TextAlign\": \"center\",\n" +
            "            \"Direction\": \"LTR\"\n" +
            "          }\n" +
            "        },\n" +
            "        {\n" +
            "          \"Type\": \"Checkbox\",\n" +
            "          \"Name\": \"ChkShowWithCanceled\",\n" +
            "          \"Title\": \"آيا قبوض ابطال شده نيز نمايش داده شوند؟\",\n" +
            "          \"IsUsedAsInputParam\": true,\n" +
            "          \"DefaultValue\": false,\n" +
            "          \"Validation\": null,\n" +
            "          \"Style\": {\n" +
            "            \"Dimension\": {\n" +
            "              \"PhoneVerticalUnit\": \"0:3\",\n" +
            "              \"PhoneHorizontalUnit\": \"2:3\",\n" +
            "              \"TabletVerticalUnit\": \"0:3\",\n" +
            "              \"TabletHorizontalUnit\": \"3:3\"\n" +
            "            },\n" +
            "            \"Type\": \"YaldaDefault|Material\",\n" +
            "            \"BackgroundColor\": \"#abff44\",\n" +
            "            \"FontColor\": \"black\",\n" +
            "            \"Font\": \"Tahoma\",\n" +
            "            \"FontSize\": \"12px\",\n" +
            "            \"IconStyle\": \"fa-calendar\",\n" +
            "            \"Height\": \"40px\",\n" +
            "            \"TextAlign\": \"center\",\n" +
            "            \"Direction\": \"LTR\"\n" +
            "          }\n" +
            "        },\n" +
            "        {\n" +
            "          \"Type\": \"TextArea\",\n" +
            "          \"Name\": \"TxtDescription\",\n" +
            "          \"Title\": \"توضيحات\",\n" +
            "          \"IsUsedAsInputParam\": true,\n" +
            "          \"DefaultValue\": null,\n" +
            "          \"Validation\": {\n" +
            "            \"IsRequired\": false\n" +
            "          },\n" +
            "          \"Style\": {\n" +
            "            \"Dimension\": {\n" +
            "              \"PhoneVerticalUnit\": \"0:6\",\n" +
            "              \"PhoneHorizontalUnit\": \"0:6\",\n" +
            "              \"TabletVerticalUnit\": \"0:6\",\n" +
            "              \"TabletHorizontalUnit\": \"0:6\"\n" +
            "            },\n" +
            "            \"Type\": \"YaldaDefault|Material\",\n" +
            "            \"BackgroundColor\": \"#abff44\",\n" +
            "            \"FontColor\": \"black\",\n" +
            "            \"Font\": \"Tahoma\",\n" +
            "            \"FontSize\": \"12px\",\n" +
            "            \"IconStyle\": \"fa-calendar\",\n" +
            "            \"Height\": \"40px\",\n" +
            "            \"TextAlign\": \"center\",\n" +
            "            \"Direction\": \"LTR\"\n" +
            "          }\n" +
            "        },\n" +
            "        {\n" +
            "          \"Type\": \"Submit\",\n" +
            "          \"Name\": \"BtnViewBillPerPeriod\",\n" +
            "          \"Title\": \"مشاهده\",\n" +
            "          \"IsUsedAsInputParam\": false,\n" +
            "          \"SubmitUrl\": null,\n" +
            "          \"OutputType\": \"None|Message|PlainJson\",\n" +
            "          \"Validation\": null,\n" +
            "          \"Style\": {\n" +
            "            \"Dimension\": {\n" +
            "              \"PhoneVerticalUnit\": \"0:6\",\n" +
            "              \"PhoneHorizontalUnit\": \"0:3\",\n" +
            "              \"TabletVerticalUnit\": \"0:3\",\n" +
            "              \"TabletHorizontalUnit\": \"0:2\"\n" +
            "            },\n" +
            "            \"Type\": \"YaldaDefault|Material\",\n" +
            "            \"BackgroundColor\": \"#abff44\",\n" +
            "            \"FontColor\": \"black\",\n" +
            "            \"Font\": \"Tahoma\",\n" +
            "            \"FontSize\": \"12px\",\n" +
            "            \"IconStyle\": \"fa-calendar\",\n" +
            "            \"Height\": \"40px\",\n" +
            "            \"TextAlign\": \"center\",\n" +
            "            \"Direction\": \"LTR\"\n" +
            "          }\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}"
}