package com.github.knextsunj.cms.util

import com.github.knextsunj.cms.domain.*
import com.github.knextsunj.cms.dto.AddressDTO
import com.github.knextsunj.cms.dto.UserPreferenceDTO
import com.github.knextsunj.cms.internalto.AddressDetailsTO
import com.github.knextsunj.cms.repository.AddressRepository
import java.time.LocalDateTime



open class MapperUtil {

    companion object {

        @JvmStatic
        fun fromAddressDTO(addressDTO: AddressDTO?): Address {
            // !! is non-null asserted call
           return buildOrUpdateAddress(toUpdate=false,address=null,addressDTO= addressDTO!!)
        }

        @JvmStatic
        fun setAddressDates(address:Address):Address {
            if(!CmsUtil.isNull(address)) {
                if(CmsUtil.isNull(address?.createdDate)) {
                    address.createdDate = LocalDateTime.now()
                }
                address.updatedDate = LocalDateTime.now()

            }
            return address
        }

        @JvmStatic
        fun save(toUpdate: Boolean,addressDetailsTO: AddressDetailsTO, addressRepository: AddressRepository):Boolean {

            if(!toUpdate) {
                return save(
                    city = addressDetailsTO.city, state = addressDetailsTO.state, customer = addressDetailsTO.customer,
                    country = addressDetailsTO.country, addressRepository = addressRepository,
                    addressType = addressDetailsTO.addressType, address = addressDetailsTO.address
                )
            }
            // Default operation is update
          var toUpdateAddress =  buildOrUpdateAddress(toUpdate=toUpdate,address=addressDetailsTO.address,addressDTO=addressDetailsTO.addressDTO)
            return save(
                city = addressDetailsTO.city, state = addressDetailsTO.state, customer = addressDetailsTO.customer,
                country = addressDetailsTO.country, addressRepository = addressRepository,
                addressType = addressDetailsTO.addressType, address = toUpdateAddress
            )
        }

        private fun save(address:Address, customer: Customer, city: City, state: State, country: Country,
                         addressType: AddressType, addressRepository: AddressRepository):Boolean {
            address.city = city
            address.state = state
            address.country = country
            address.addressType = addressType
            address.customer = customer
            val savedAddress = addressRepository.save(address)
            return null!=savedAddress
        }

        private  fun buildOrUpdateAddress(addressDTO:AddressDTO,toUpdate:Boolean,address:Address?):Address {

            if(!toUpdate) {
                var address = Address()
                setAddressValues(addressDTO=addressDTO,address=address)
            }
            else {
                // !! is non-null asserted call
                setAddressValues(addressDTO=addressDTO,address= address!!)
            }

            return address!!
        }


        private fun setAddressValues(address:Address,addressDTO:AddressDTO):Address {
            /**
             * The safe call(?) operator checks if addressDTO is null,if yes then null is assigned to address.name
             */
            address.name = addressDTO?.name
            address.deleted = addressDTO?.deleted
            address.street = addressDTO?.street
            address.locality = addressDTO?.locality
            address.area = addressDTO?.area
            address.pincode = addressDTO?.pincode

            return address
        }

    }


    }
