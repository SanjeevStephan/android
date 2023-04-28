package com.sanjeev.stephan.murmu.kotlinbeginnercodes

/**
 * @author Sanjeev Stephan Murmu
 * @since 08-Feb-2020
 */
import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.databinding.FragmentAndroidSqliteDatabaseBinding
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.sqlitedatabase.MySQLiteDatabaseHandler
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.sqlitedatabase.EmpModelClass
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.sqlitedatabase.MySQLiteListAdapter
import kotlinx.android.synthetic.main.fragment_android_sqlite_database.*

/**
 * Kotlin Android SQLite
 * @see https://www.javatpoint.com/kotlin-android-sqlite-tutorial
 *
 * SQLite is an open-source relational database that is used to perform database operations
 * on Android devices such as storing,
 * manipulating or retrieving persistent data from the database.
 * @see EmpModelClass
 * @see MySQLiteDatabaseHandler
 * @see MySQLiteListAdapter
 * @see R.layout.fragment_android_sqlite_database
 * @see R.layout.layout_custom_sqlite_row_item
 * @see R.layout.layout_custom_sqlite_delete_dialog
 * @see R.layout.layout_custom_sqlite_input_dialog
 */
class Android_SQLiteDatabase: Fragment(),View.OnClickListener
{

    lateinit var binding: FragmentAndroidSqliteDatabaseBinding
    lateinit var inputId: EditText
    lateinit var inputName: EditText
    lateinit var inputEmail: EditText
    lateinit var btnSave: Button
    lateinit var btnView: Button
    lateinit var btnDelete: Button
    lateinit var btnUpdate: Button
    lateinit var listView: ListView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_android_sqlite_database, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputId = binding.uId
        inputName = binding.uName
        inputEmail = binding.uEmail
        btnSave = binding.idBtnSave
        btnView = binding.idBtnView
        btnUpdate = binding.idBtnUpdate
        btnDelete = binding.idBtnUpdate
        listView = binding.idListView

        btnSave.setOnClickListener(this)
        btnView.setOnClickListener(this)
        btnUpdate.setOnClickListener(this)
        btnDelete.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.id_btn_save -> {
                saveRecord(v)
                viewRecord(v)
            }
            R.id.id_btn_view -> viewRecord(v)
            R.id.id_btn_update -> updateRecord(v)
            R.id.id_btn_delete -> deleteRecord(v)
        }
    }

    //method for saving records in database
    private fun saveRecord(view: View){
        val id = inputId.text.toString()
        val name = inputName.text.toString()
        val email = inputEmail.text.toString()
        val databaseHandler: MySQLiteDatabaseHandler = MySQLiteDatabaseHandler(view.context as Activity)
        if(id.trim()!="" && name.trim()!="" && email.trim()!=""){

            val status = databaseHandler.addEmployee(EmpModelClass(Integer.parseInt(id),name, email))
            if(status > -1){
                Toast.makeText(view.context as Activity,"record save",Toast.LENGTH_LONG).show()
                u_id.text.clear()
                u_name.text.clear()
                u_email.text.clear()
            }
        }else{
            Toast.makeText(view.context as Activity,"id or name or email cannot be blank",Toast.LENGTH_LONG).show()
        }

    }
    //method for read records from database in ListView
    private fun viewRecord(view: View){
        //creating the instance of DatabaseHandler class
        val databaseHandler: MySQLiteDatabaseHandler= MySQLiteDatabaseHandler(view.context as Activity)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val emp: List<EmpModelClass> = databaseHandler.viewEmployee()
        val empArrayId = Array<String>(emp.size){"0"}
        val empArrayName = Array<String>(emp.size){"null"}
        val empArrayEmail = Array<String>(emp.size){"null"}
        var index = 0
        for(e in emp){
            empArrayId[index] = e.userId.toString()
            empArrayName[index] = e.userName
            empArrayEmail[index] = e.userEmail
            index++
        }
        //creating custom ArrayAdapter
        val myListAdapter = MySQLiteListAdapter(view.context as Activity,empArrayId,empArrayName,empArrayEmail)
        listView.adapter = myListAdapter
        listView.setOnItemClickListener{
            _, _, position, _ ->

            val id = empArrayId[position]
            val name = empArrayName[position]
            val email = empArrayEmail[position]
            val showText = "Selected : ID[$id] | Name [$name] | Email[$email]"
            setToast(view.context as Activity, showText)
            setLog(showText)
        }
    }

    //method for updating records based on user id
    private fun updateRecord(view: View){
        val dialogBuilder = AlertDialog.Builder(view.context as Activity)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.layout_custom_sqlite_input_dialog, null)
        dialogBuilder.setView(dialogView)

        val edtId = dialogView.findViewById(R.id.updateId) as EditText
        val edtName = dialogView.findViewById(R.id.updateName) as EditText
        val edtEmail = dialogView.findViewById(R.id.updateEmail) as EditText

        dialogBuilder.setTitle("Update Record")
        dialogBuilder.setMessage("Enter data below")
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { _, _ ->

            val updateId = edtId.text.toString()
            val updateName = edtName.text.toString()
            val updateEmail = edtEmail.text.toString()
            //creating the instance of DatabaseHandler class
            val databaseHandler: MySQLiteDatabaseHandler= MySQLiteDatabaseHandler(view.context as Activity)
            if(updateId.trim()!="" && updateName.trim()!="" && updateEmail.trim()!=""){
                //calling the updateEmployee method of DatabaseHandler class to update record
                val status = databaseHandler.updateEmployee(EmpModelClass(Integer.parseInt(updateId),updateName, updateEmail))
                if(status > -1){
                    Toast.makeText(view.context as Activity,"record update",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(view.context as Activity,"id or name or email cannot be blank",Toast.LENGTH_LONG).show()
            }

        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }
    //method for deleting records based on id
    private fun deleteRecord(view: View){
        //creating AlertDialog for taking user id
        val dialogBuilder = AlertDialog.Builder(view.context as Activity)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.layout_custom_sqlite_delete_dialog, null)
        dialogBuilder.setView(dialogView)

        val dltId = dialogView.findViewById(R.id.deleteId) as EditText
        dialogBuilder.setTitle("Delete Record")
        dialogBuilder.setMessage("Enter id below")
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->

            val deleteId = dltId.text.toString()
            //creating the instance of DatabaseHandler class
            val databaseHandler: MySQLiteDatabaseHandler= MySQLiteDatabaseHandler(view.context as Activity)
            if(deleteId.trim()!=""){
                //calling the deleteEmployee method of DatabaseHandler class to delete record
                val status = databaseHandler.deleteEmployee(EmpModelClass(Integer.parseInt(deleteId),"",""))
                if(status > -1){
                    setToast(view.context as Activity,"record deleted")
                }
            }else{

                setToast(view.context as Activity,"id or name or email cannot be blank")
            }

        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }
}