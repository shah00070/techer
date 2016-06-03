package com.schoolteacher.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.schoolteacher.R;

/**
 * Created by perveen akhtar on 5/9/2016.
 */
public class mysyallabus extends Fragment {

      String[] text1 = { "Mathematics", "Science", "Social Science",
            "Hindi", "English"};
    String[] val1 = { "1.  Rational Numbers\n" +
            "\n" +
            " • Properties of rational numbers (including identities). Using general form of expression to describe properties.\n" +
            "\n" +
            "• Consolidation of operations on rational numbers.\n" +
            "\n" +
            "• Representation of rational numbers on the number line\n" +
            "\n" +
            "• Between any two rational numbers there lies another rational number (Making children see that if we take two rational numbers then unlike for whole numbers, in this case you can keep finding more and more\n" +
            "numbers that lie between them.)\n" +
            "\n" +
            "• Word problem (higher logic,two operations, including ideas like areas)\n" +
            "\n" +
            "\n" +
            "2.  Powers \n" +
            "\n" +
            "• Integers as exponents\n" +
            "\n" +
            "• Laws of exponents with integral powers\n" +
            "\n" +
            "\n" +
            "3.  Squares, Square roots, Cubes, Cube roots\n" +
            "\n" +
            "• Square and Square roots\n" +
            "\n" +
            "• Square roots using factor method and division method for numbers containing \t\n" +
            "(a) no more than total 4 digits and \n" +
            "(b) no more than 2 decimal places\n" +
            "\n" +
            "• Cubes and cubes roots (only factor method for numbers containing at most 3 digits)\n" +
            "\n" +
            "4.  Playing with numbers\n" +
            "\n" +
            "• Writing and understanding a 2 and 3 digit number in generalized form (100a + 10b + c , where a,b, c can\n" +
            " be only digit 0-9) and engaging with various puzzles concerning this. (Like finding the missing numerals \n" +
            "represented by alphabets in sums involving any of the four operations.) Children to solve and create problems\n" +
            "and puzzles.\n" +
            "\n" +
            "\n" +
            "5.  Algebra:\n" +
            "\n" +
            "• Multiplication and division of algebraic exp.(Coefficient should be integers)\n" +
            "• Some common errors (e.g. 2 +x ≠\uF0202x, 7x + y ≠\uF0207xy )\n" +
            "\n" +
            "6.  Ratio and Proportion\n" +
            "\n" +
            "Slightly advanced problems involving applications on percentages, profit & loss, overhead expenses, Discount, tax.\n" +
            "\n" +
            "\n" +
            "7. Geometry \n" +
            "\n" +
            "• Properties of quadrilaterals – Sum of angles of a quadrilateral is equal to 3600 (By verification)\n" +
            "• Properties of parallelogram (By verification)\n" +
            "(i) Opposite sides of a parallelogram are equal,\n" +
            "ii) Opposite angles of a parallelogram are equal,\n" +
            "iii) Diagonals of a parallelogram bisect each other.\n" +
            "\n",








            "Syllabus:\n" +
                    "1. Food (Periods - 22)\n" +
                    "Crop production\n" +
                    "Questions\tCrop production: How are different food crops produced?\n" +
                    "What are the various foods we get from animal sources?\n" +
                    "Key Concepts\tCrop production: Soil preparation, selection of seeds, sowing, applying fertilizers, irrigation, weeding, harvesting and storage; nitrogen fixation, nitrogen cycle.\n" +
                    "Resources\tInteraction and discussion with local men and women farmers about farming and farm practices; visit to cold storage, go- downs; visit to any farm/ nursery/ garden.\n" +
                    "Activities/Processes\tPreparing herbarium specimens of some crop plants; collection of some seeds etc; preparing a table/chart on different irrigation practices and sources of water in different parts of India; looking at roots of any legume crop for nodules, hand section of nodules.\n" +
                    "Micro-organisms\n" +
                    "Questions\tWhat living organisms do we see under a microscope in a drop of water? What helps make curd? How does food go bad? How do we preserve food?\n" +
                    "Key Concepts\tMicro organisms – useful and harmful.\n" +
                    "Resources\tMicroscope, kit materials; information about techniques of food preservation.\n" +
                    "Activities/Processes\tMaking a lens with a bulb; Observation of drop of water, curd, other sources, bread mould, orange mould under the microscope; experiment showing fermentation of dough – increase in volume (using yeast) – collect gas in balloon, test in lime water.\n" +
                    "2. Materials (Periods - 26)\n" +
                    "Materials in daily life\n" +
                    "Questions\tAre some of our clothes synthetic?\n" +
                    "How are they made?\n" +
                    "Where do the raw materials come from?\n" +
                    "Do we use other materials that are synthetic?\n" +
                    "Do we use cloth (fabric) for purposes other than making clothes to wear?\n" +
                    "What kind of fabric do we see around us?\n" +
                    "What are they used for?\n" +
                    "Key Concepts\tSynthetic clothing materials. Other synthetic materials, especially plastics; usefulness of plastics and problems associated with their excessive use.There are a variety of fibrous materials in use. A material is chosen based on desired property.\n" +
                    "Resources\tSharing of prior knowledge, source materials on petroleum products.Collection of material from neighbourhood or should be part of the kit.\n" +
                    "Activities/Processes\tSurvey on use of synthetic materials. Discussion. Testing various materials – for action of water, reaction on heating, effect of flame, electrical conductivity, thermal conductivity, tensile strength.\n" +
                    "Different kinds of materials and their reactions.\n" +
                    "Questions\tCan a wire be drawn out of wood? Do copper or aluminium also rust like iron? What is the black material inside a pencil? Why are electrical wires made of aluminium or copper?\n" +
                    "Key Concepts\tMetals and non-metals.\n" +
                    "Resources\tKit items.\n" +
                    "\n" +
                    "\n",

            "Unit 1: India and the Contemporary World \n" +
                    "\n" +
                    "In Sub-unit 1.1 you are required to choose any two themes. In that sub-unit, theme 3 is compulsory and for second theme you are required to choose any one from the first two themes. In Sub Units 1.2 and 1.3 you are required to choose any one theme from each. Thus, you are required to study four themes in all.\n" +
                    "\n" +
                    "Term I\n" +
                    "\n" +
                    "Sub-unit 1.2: Livelihoods, Economies and Societies\n" +
                    "Any one of the following themes:\n" +
                    "4. The making of Global World: \n" +
                    "(a) Contrast between the form of industrialization in Britain and India. \n" +
                    "(b) Relationship between handicrafts and industrial production, formal and informal sectors. \n" +
                    "(c) Livelihood of workers. Case studies : Britain and India. (Chapter 4)\n" +
                    "5. The Age of Indutrialisation: \n" +
                    "(a) Patterns of urbanization \n" +
                    "(b) Migration and the growth of towns. \n" +
                    "(c) Social change and urban life.\n" +
                    " (d) Merchants, middle classes, workers and urban poor. (Chapter 5)\n" +
                    "Case Studies: London and Bombay in the nineteenth and twentieth century.\n" +
                    "6. Work, Life and Leisure:\n" +
                    " (a) Expansion and integration of the world market in the nineteenth and early twentieth century. \n" +
                    "(b) Trade and economy between the two Wars. \n" +
                    "(c) Shifts after the 1950s. \n" +
                    "(d) Implications of globalization for livelihood patterns.\n" +
                    "Case study: The post War International Economic order, 1945 to 1960s. (Chapter 6)\n" +
                    "Sub-unit 1.3 : Everyday Life, Culture and Politics\n" +
                    "Any one of the following themes:\n" +
                    "7. Print Culture and the Modern World: \n" +
                    "(a) The history of print in Europe. \n" +
                    "(b) The growth of press in nineteenth century India. \n" +
                    "(c) Relationship between print culture, public debate and politics. (Chapter 7)\n" +
                    "8. Novels, Society and History: \n" +
                    "(a) Emergence of the novel as a genre in the west. (\n" +
                    "b) The relationship between the novel and changes in modern society. \n" +
                    "(c) Early novels in nineteenth century India. \n" +
                    "(d) A study of two or three major writers. (Chapter 8)\n" +
                    "\n" +
                    "Term II\n" +
                    "Sub-unit 1.1: Events and processes:\n" +
                    " \n",


            "Hindi Syllabus",



            "SECTION A: READING\n" +
                    "Qs 1-2. This section will have two unseen passages of a total length of 700-750. The arrangement within the reading section is as follows:\n" +
                    "Q. 1: A Factual passage of 300-350 words with eight very short answer type questions. [8 marks]\n" +
                    "Q. 2: A Discursive passage of 350-400 words with four short answer type questions to test inference, evaluation and analysis and four MCQs to test vocabulary. [12 Marks]\n" +
                    "SECTION B: WRITING AND GRAMMAR\n" +
                    "Q. 3: Letter to the editor / article in about 100-120 words based on visual or verbal stimulus. [5 marks]\n" +
                    "Q. 4: Writing a short story based on a given outline or cue/s in about 150-200 words. [10 marks]\n" +
                    "The Grammar syllabus will include the following areas in classes IX and X.\n" +
                    "1. Tenses\n" +
                    "2. Modals (have to/had to, must, should, need, ought to and their negative forms)\n" +
                    "3. Use of passive voice\n" +
                    "4. Subject – verb concord\n" +
                    "5. Reporting\n" +
                    "(i) Commands and requests\n" +
                    "(ii) Statements\n" +
                    "(iii) Questions\n" +
                    "6. Clauses:\n" +
                    "7. Noun clauses\n" +
                    "8. Adverb clauses of condition and time\n" +
                    "9. Relative clauses\n" +
                    "10. Determiners, and\n" +
                    "11. Prepositions\n" +
                    "The above items may be tested through test types as given below:\n" +
                    "Q. 5: Gap filling with one or two words to test Prepositions, Articles, Conjunctions and Tenses. [3 marks]\n" +
                    "Q. 6: Editing or omission. [4 marks]\n" +
                    "Q. 7: Sentences reordering or Sentence Transformation in context. [3 marks]\n" +
                    "SECTION C: LITERATURE TEXTBOOKS AND LONG READING TEXT\n" +
                    "Q.8: One out of two extracts from prose/poetry/drama for reference to context. Three very short answer questions. [3 marks]\n" +
                    "One mark in each extract will be for vocabulary. One question will be used for testing local and global comprehension  and one question will be on interpretation.\n" +
                    "Q.9: Four short answer type questions from FIRST FLIGHT & FOOTPRINTS WITHOUT FEET (two from each) to test local and global comprehension of theme and ideas (30-40 words each). [2x4=8 marks]\n" +
                    "Q.10: One out of two long answer type questions to assess how the values inherent in the texts have been brought out (FIRST FLIGHT & FOOTPRINTS WITHOUT FEET). Creativity, imagination and extrapolation beyond the text and across the texts will be assessed. (80-100 words). [4 marks]\n" +
                    "Q.11: One out of two Long Answer Questions on theme or plot or character involving interpretation and inference in about 100-120 words based on prescribed novel. [10 marks]\n" +
                    "\n"};


    Spinner spinner1;
    TextView textView1;
    private View rootView;
/*
    Spinner s1,s2;
*/



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.mysyllabus, container,
                false);


        setUiOnScreen();
        return rootView;
    }

    private void setUiOnScreen() {

        textView1 = (TextView) rootView.findViewById(R.id.text1);
        spinner1 = (Spinner) rootView.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, text1);
       // adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(onItemSelectedListener1);

    }

    AdapterView.OnItemSelectedListener onItemSelectedListener1 =
            new AdapterView.OnItemSelectedListener(){

                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    String s1 = String.valueOf(val1[position]);
                    textView1.setText(s1);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {}

            };





}
