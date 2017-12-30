package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.olleg.functioncalculator.MainActivity;
import com.example.olleg.functioncalculator.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FunctionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FunctionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FunctionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    SymbolClickListener symbolListener = new SymbolClickListener();

    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;

    private Button buttonAdd;
    private Button buttonSub;
    private Button buttonDot;
    private Button buttonDivide;
    private Button buttonMultiply;
    private Button buttonOpenBracket;
    private Button buttonCloseBracket;
    private Button buttonDeleteSymbol;
    private Button buttonDeleteAll;
    private Button buttonEquals;

    private MainActivity parentActivity;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FunctionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FunctionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FunctionFragment newInstance(String param1, String param2) {
        FunctionFragment fragment = new FunctionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_function, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        button0 = (Button) getView().findViewById(R.id.num0);
        button1 = (Button) getView().findViewById(R.id.num1);
        button2 = (Button) getView().findViewById(R.id.num2);
        button3 = (Button) getView().findViewById(R.id.num3);
        button4 = (Button) getView().findViewById(R.id.num4);
        button5 = (Button) getView().findViewById(R.id.num5);
        button6 = (Button) getView().findViewById(R.id.num6);
        button7 = (Button) getView().findViewById(R.id.num7);
        button8 = (Button) getView().findViewById(R.id.num8);
        button9 = (Button) getView().findViewById(R.id.num9);

        buttonAdd = (Button) getView().findViewById(R.id.operationAdd);
        buttonSub = (Button) getView().findViewById(R.id.operationSub);
        buttonDot = (Button) getView().findViewById(R.id.dot);
        buttonDivide = (Button) getView().findViewById(R.id.operationDivide);
        buttonMultiply = (Button) getView().findViewById(R.id.operationMultiply);
        buttonOpenBracket = (Button) getView().findViewById(R.id.openBracket);
        buttonCloseBracket = (Button) getView().findViewById(R.id.closeBracket);
        buttonDeleteSymbol = (Button) getView().findViewById(R.id.deleteSymbol);
        buttonDeleteAll = (Button) getView().findViewById(R.id.clearAll);
        buttonEquals = (Button) getView().findViewById(R.id.operationCalculate);

        button0.setOnClickListener(symbolListener);
        button1.setOnClickListener(symbolListener);
        button2.setOnClickListener(symbolListener);
        button3.setOnClickListener(symbolListener);
        button4.setOnClickListener(symbolListener);
        button5.setOnClickListener(symbolListener);
        button6.setOnClickListener(symbolListener);
        button7.setOnClickListener(symbolListener);
        button8.setOnClickListener(symbolListener);
        button9.setOnClickListener(symbolListener);

        buttonAdd.setOnClickListener(symbolListener);
        buttonSub.setOnClickListener(symbolListener);
        buttonDot.setOnClickListener(symbolListener);
        buttonDivide.setOnClickListener(symbolListener);
        buttonMultiply.setOnClickListener(symbolListener);
        buttonOpenBracket.setOnClickListener(symbolListener);
        buttonCloseBracket.setOnClickListener(symbolListener);

        buttonDeleteSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.deleteSymbol();
            }
        });

        buttonDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.clearAll();
            }
        });
        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.calculate();
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parentActivity = (MainActivity)context;
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class SymbolClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.num0:{
                    parentActivity.numSymbol("0");
                    break;
                }
                case R.id.num1:{
                    parentActivity.numSymbol("1");
                    break;
                }
                case R.id.num2:{
                    parentActivity.numSymbol("2");
                    break;
                }
                case R.id.num3:{
                    parentActivity.numSymbol("3");
                    break;
                }
                case R.id.num4:{
                    parentActivity.numSymbol("4");
                    break;
                }
                case R.id.num5:{
                    parentActivity.numSymbol("5");
                    break;
                }
                case R.id.num6:{
                    parentActivity.numSymbol("6");
                    break;
                }
                case R.id.num7:{
                    parentActivity.numSymbol("7");
                    break;
                }
                case R.id.num8:{
                    parentActivity.numSymbol("8");
                    break;
                }
                case R.id.num9:{
                    parentActivity.numSymbol("9");
                    break;
                }
                case R.id.openBracket:{
                    parentActivity.numSymbol("(");
                    break;
                }
                case R.id.closeBracket:{
                    parentActivity.numSymbol(")");
                    break;
                }
                case R.id.operationAdd:{
                    parentActivity.numSymbol("+");
                    break;
                }
                case R.id.operationDivide:{
                    parentActivity.numSymbol("/");
                    break;
                }
                case R.id.operationMultiply:{
                    parentActivity.numSymbol("*");
                    break;
                }
                case R.id.operationSub:{
                    parentActivity.numSymbol("-");
                    break;
                }
                case R.id.dot:{
                    parentActivity.numSymbol(".");
                    break;
                }

            }
        }
    }
}
