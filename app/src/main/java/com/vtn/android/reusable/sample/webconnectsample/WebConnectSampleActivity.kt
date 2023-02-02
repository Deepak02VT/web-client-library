package com.vtn.android.reusable.sample.webconnectsample

import android.os.Bundle
import android.view.SoundEffectConstants
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vtn.android.R
import com.vtn.android.databinding.ActivityWebconnectSampleBinding
import com.vtn.android.databinding.LayoutListItemBinding
import com.vtn.android.reusable.sample.webconnectsample.adapter.BaseRecycleViewAdapter
import com.vtn.android.reusable.sample.webconnectsample.model.Data
import com.vtn.android.reusable.sample.webconnectsample.utils.Alert.createDialog
import com.vtn.android.reusable.sample.webconnectsample.utils.Loader
import com.vtn.android.reusable.sample.webconnectsample.viewmodel.WebConnectViewModel
import com.vtn.android.reusable.sample.webconnectsample.webapi.ApiState



class WebConnectSampleActivity : AppCompatActivity() {

    private lateinit var adapter: BaseRecycleViewAdapter<*>
    private lateinit var binding: ActivityWebconnectSampleBinding
    private var viewModel:WebConnectViewModel?=null
    private var loader: Loader?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@WebConnectSampleActivity, R.layout.activity_webconnect_sample)
        viewModel = ViewModelProvider(this)[WebConnectViewModel::class.java]
        loader = Loader(this,lifecycle)
        binding.userList.layoutManager =  LinearLayoutManager(
            this
        )
        onUserList()
        getUserList()
        onCreateUser()
        onUpdateUser()
        onDeleteUser()
        setOnCreateUserClick()
    }

    private fun onDeleteUser() {
        viewModel?.deleteUser?.observe(this){
            when(it){
                is ApiState.Loading->{
                    loader?.show()
                }
                is ApiState.SuccessResponse->{
                    loader?.hide()
                    showMessage("Deleted User")
                }
                is ApiState.OnFailure->{
                    showMessage(it.error)
                    loader?.hide()
                }
                is ApiState.OnError->{
                    showMessage(it.throwable?.message)
                    loader?.hide()
                }
            }
        }
    }

    private fun onUpdateUser() {
        viewModel?.updateUser?.observe(this){
            when(it){
                is ApiState.Loading->{
                    loader?.show()
                }
                is ApiState.SuccessResponse->{
                    loader?.hide()
                    showMessage("User Updated")
                }
                is ApiState.OnFailure->{
                    showMessage(it.error)
                    loader?.hide()
                }
                is ApiState.OnError->{
                    showMessage(it.throwable?.message)
                    loader?.hide()
                }
            }
        }
    }

    private fun onCreateUser() {
        viewModel?.createUser?.observe(this){
            when(it){
                is ApiState.Loading->{
                    loader?.show()
                }
                is ApiState.SuccessResponse->{
                    loader?.hide()
                    showMessage("User Created")
                }
                is ApiState.OnFailure->{
                    showMessage(it.error)
                    loader?.hide()
                }
                is ApiState.OnError->{
                    showMessage(it.throwable?.message)
                    loader?.hide()
                }
            }
        }
    }

    private fun setOnCreateUserClick() {
       binding.toolbar.createUser.setOnClickListener {
           viewModel?.createUser()
       }
    }

    private fun getUserList() {
        viewModel?.getUsers()
    }

    private fun onUserList(){
        viewModel?.users?.observe(this){
            when(it){
                is ApiState.Loading->{
                    loader?.show()
                }
                is ApiState.SuccessResponse->{
                    loader?.hide()
                    it.data?.let { data -> data.data?.let { user -> setAdapter(user) } }
                }
                is ApiState.OnFailure->{
                    showMessage(it.error)
                    loader?.hide()
                }
                is ApiState.OnError->{
                    showMessage(it.throwable?.message)
                    loader?.hide()
                }
            }
        }
    }

    private fun showMessage(message:String?){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    private fun setAdapter(users: List<Data>){
            adapter = object : BaseRecycleViewAdapter<ViewDataBinding>() {
                override fun bindData(position: Int, baseViewHolder: BaseViewHolder?) {
                    if (baseViewHolder?.binding is LayoutListItemBinding) {
                        val layoutListItem = baseViewHolder.binding as LayoutListItemBinding
                        val user = users[position]
                        layoutListItem.userEmail.text = user.email
                        val name = "${user.firstName} ${user.lastName}"
                        layoutListItem.userName.text = name

                        layoutListItem.userListRootLayout.setOnClickListener {
                            createDialog(
                                this@WebConnectSampleActivity,
                                getString(R.string.alert),
                                getString(R.string.update_alert),
                                getString(R.string.yes),
                                getString(R.string.no),
                                false
                            ) { dialog ->
                                dialog.dismiss()
                                viewModel?.updateUser(user.id)
                            }
                        }

                        layoutListItem.deleteUser.setOnClickListener {
                            viewModel?.deleteUser(user.id)
                        }
                    }
                }

                override val count: Int
                    get() = users.size

                override val inflateLayout: Int
                    get() = R.layout.layout_list_item

                override fun getItemId(position: Int): Long {
                    return position.toLong()
                }

                override fun getItemViewType(position: Int): Int {
                    return position
                }
            }
            binding.userList.adapter = adapter
            binding.userList.playSoundEffect(SoundEffectConstants.CLICK)
            binding.userList.adapter?.notifyDataSetChanged()
    }
}
