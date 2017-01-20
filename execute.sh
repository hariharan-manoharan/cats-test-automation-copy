 #!/bin/bash
# execute.sh

#Parameters
CUSTOMER=$1
ENVIRONMENT=$2
PLATFORM=$3
TEST=$4
LOCAL=$5
DEVICE=$6
LOCALOS=$7

#Test Rail Config
TESTRAIL=false
XOTESTID=2831
VZWTESTID=2804
VZWBVTTESTID=2819

ENVIRONMENTPATH='';

if [ "$CUSTOMER" == 'Airtel' ]; then

	if [ "$PLATFORM" == 'Android' ]; then

		if [ "$TEST" == 'BVT' ]; then
			mvn clean install -Dtest=Routines/Android/Airtel/Test/AirtelBVTTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE #-DtestRail.testPlanId=2700 -DtestRail.enabled=$TESTRAIL
		elif [ "$TEST" == 'Inventory' ]; then
			mvn clean install -Dtest=Routines/Android/Airtel/Test/InventoryTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE
		elif [ "$TEST" == 'Stock' ]; then
			mvn clean install -Dtest=Routines/Android/Airtel/Test/StockTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE		
		elif [ "$TEST" == 'Status' ]; then
			mvn clean install -Dtest=Routines/Android/Airtel/Test/StatusChangeTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE		
		elif [ "$TEST" == 'Inquiry' ]; then
			mvn clean install -Dtest=Routines/Android/Airtel/Test/InquiryTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE		
		elif [ "$TEST" == 'Repair' ]; then
			mvn clean install -Dtest=Routines/Android/Airtel/Test/RepairTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE	
		elif [ "$TEST" == 'Container' ]; then
			mvn clean install -Dtest=Routines/Android/Airtel/Test/ContainerTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE		
		elif [ "$TEST" == 'Field' ]; then
			mvn clean install -Dtest=Routines/Android/Airtel/Test/FieldTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE		
		elif [ "$TEST" == 'Dispatch' ]; then
			mvn clean install -Dtest=Routines/Android/Airtel/Test/DispatchTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE		
		elif [ "$TEST" == 'Assembly' ]; then
			mvn clean install -Dtest=Routines/Android/Airtel/Test/AssemblyTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE	
		elif [ "$TEST" == 'Audit' ]; then
			mvn clean install -Dtest=Routines/Android/Airtel/Test/AuditTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE	
		elif [ "$TEST" == 'Receive' ]; then
			mvn clean install -Dtest=Routines/Android/Airtel/Test/ReceiveTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE	
		elif [ "$TEST" == 'TransferRequest' ]; then
			mvn clean install -Dtest=Routines/Android/Airtel/Test/Sample/TransferRequestTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE # Newly added		
		else 
			echo "Cannot find $CUSTOMER Test Suite $TEST for $PLATFORM"
		fi

	else 

		echo "$CUSTOMER does not have support for $PLATFORM"	
	
	fi

elif [ "$CUSTOMER" == 'Core' ]; then
	
	if [ "$PLATFORM" == 'Android' ]; then
		
		if [ "$TEST" == 'BVT' ]; then
			mvn clean install -Dtest=Pages/Android/Test/BVTTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=2710 -DtestRail.enabled=$TESTRAIL
		else 
			echo "Cannot find $CUSTOMER Test Suite $TEST for $PLATFORM"
		fi

	elif [ "$PLATFORM" == 'iOS' ]; then
		
		if [ "$TEST" == 'BVT' ]; then
			mvn clean install -Dtest=Pages/iOS/Test/BVTTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE
		else 
			echo "Cannot find $CUSTOMER Test Suite $TEST for $PLATFORM"
		fi
	
	fi

elif [ "$CUSTOMER" == 'Verizon' ]; then

	if [ "$PLATFORM" == 'Android' ]; then
		if [ "$TEST" == 'BVT' ]; then
			mvn clean install -Dtest=Routines/Android/Verizon/Test/BVTTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=$VZWBVTTESTID -DtestRail.enabled=$TESTRAIL
		elif [ "$TEST" == 'BVT2' ]; then
			mvn clean install -Dtest=Routines/Android/Verizon/Test/BVTTest2 -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=$VZWBVTTESTID -DtestRail.enabled=$TESTRAIL	
		elif [ "$TEST" == 'BVT3' ]; then
			mvn clean install -Dtest=Routines/Android/Verizon/Test/BVTTest3 -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=$VZWBVTTESTID -DtestRail.enabled=$TESTRAIL	
		elif [ "$TEST" == 'ExpressStock' ]; then
			mvn clean install -Dtest=Routines/Android/Verizon/Test/ExpressStockTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=$VZWTESTID -DtestRail.enabled=$TESTRAIL
		elif [ "$TEST" == 'Transfer' ]; then
			mvn clean install -Dtest=Routines/Android/Verizon/Test/TransferTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=$VZWTESTID -DtestRail.enabled=$TESTRAIL
		elif [ "$TEST" == 'Container' ]; then
			mvn clean install -Dtest=Routines/Android/Verizon/Test/ContainerTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=$VZWTESTID -DtestRail.enabled=$TESTRAIL	
		elif [ "$TEST" == 'CaseBreak' ]; then
			mvn clean install -Dtest=Routines/Android/Verizon/Test/CaseBreakTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=$VZWTESTID -DtestRail.enabled=$TESTRAIL
		elif [ "$TEST" == 'PO' ]; then
			mvn clean install -Dtest=Routines/Android/Verizon/Test/POReceiveTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=$VZWTESTID -DtestRail.enabled=$TESTRAIL
		elif [ "$TEST" == 'Pick' ]; then
			mvn clean install -Dtest=Routines/Android/Verizon/Test/InventoryPickingTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE	-DtestRail.testPlanId=$VZWTESTID -DtestRail.enabled=$TESTRAIL
		elif [ "$TEST" == 'Unpick' ]; then
			mvn clean install -Dtest=Routines/Android/Verizon/Test/InventoryUnpickTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE	-DtestRail.testPlanId=$VZWTESTID -DtestRail.enabled=$TESTRAIL
		elif [ "$TEST" == 'Ship' ]; then
			mvn clean install -Dtest=Routines/Android/Verizon/Test/InventoryShippingTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE	-DtestRail.testPlanId=$VZWTESTID -DtestRail.enabled=$TESTRAIL	
		elif [ "$TEST" == 'Retirement' ]; then
			mvn clean install -Dtest=Routines/Android/Verizon/Test/RetirementTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=$VZWTESTID -DtestRail.enabled=$TESTRAIL
		elif [ "$TEST" == 'Asset' ]; then
			mvn clean install -Dtest=Routines/Android/Verizon/Test/AssetTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=$VZWTESTID  -DtestRail.enabled=$TESTRAIL
		elif [ "$TEST" == 'UpdateAsset' ]; then
			mvn clean install -Dtest=Routines/Android/Verizon/Test/UpdateAssetTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=$VZWTESTID -DtestRail.enabled=$TESTRAIL
		elif [ "$TEST" == 'UpdateAssetSysAdmin' ]; then
			mvn clean install -Dtest=Routines/Android/Verizon/Test/UpdateAssetSysAdminTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=$VZWTESTID -DtestRail.enabled=$TESTRAIL
		elif [ "$TEST" == 'ChangeAsset' ]; then
			mvn clean install -Dtest=Routines/Android/Verizon/Test/ChangeAssetTagTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=$VZWTESTID  -DtestRail.enabled=$TESTRAIL
		elif [ "$TEST" == 'Quarantine' ]; then
			mvn clean install -Dtest=Routines/Android/Verizon/Test/QuarantineTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=$VZWTESTID -DtestRail.enabled=$TESTRAIL
		elif [ "$TEST" == 'Repair' ]; then
			mvn clean install -Dtest=Routines/Android/Verizon/Test/RepairTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=$VZWTESTID  -DtestRail.enabled=$TESTRAIL			
		else 
			echo "Cannot find $CUSTOMER Test Suite $TEST for $PLATFORM"
		fi

	elif [ "$PLATFORM" == 'iOS' ]; then
		
		if [ "$TEST" == 'BVT' ]; then
			mvn clean install -Dtest=Pages/iOS/Test/BVTTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL
		else 
			echo "Cannot find $CUSTOMER Test Suite $TEST for $PLATFORM"
		fi
	elif [ "$PLATFORM" == 'Web' ]; then
		
		if [ "$TEST" == 'MR' ]; then
			mvn clean install -Dtest=Pages/Web/Test/MaterialsRequestTest.java -DuserName=catsadm -DpassWord=catscats11 -Denvironment=vzw -Dlocal=true -Dos=$LOCALOS
		else 
			echo "Cannot find $CUSTOMER Test Suite $TEST for $PLATFORM"
		fi	
	fi

elif [ "$CUSTOMER" == 'XO' ]; then

	if [ "$PLATFORM" == 'Web' ]; then
		
		if [ "$TEST" == 'Requisitions' ]; then
			mvn clean install -Dtest=Pages/Web/Test/RequisitionsHomeTest.java -DuserName=catsadm -DpassWord=catscats11 -Denvironment=xo -DtestRail.testPlanId=1405 -DtestRail.enabled=false		
		else 
			echo "Cannot find $CUSTOMER Test Suite $TEST for $PLATFORM"
		fi
	elif [ "$PLATFORM" == 'Android' ]; then
		if [ "$TEST" == 'BVT' ]; then
			mvn clean install -Dtest=Routines/Android/XO/Test/BVTTest -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=$XOTESTID -DtestRail.enabled=$TESTRAIL
		elif [ "$TEST" == 'BVT2' ]; then
			mvn clean install -Dtest=Routines/Android/XO/Test/BVTTest2 -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=$XOTESTID -DtestRail.enabled=$TESTRAIL
		elif [ "$TEST" == 'BVT3' ]; then
			mvn clean install -Dtest=Routines/Android/XO/Test/BVTTest3 -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=$XOTESTID -DtestRail.enabled=$TESTRAIL
		elif [ "$TEST" == 'BVT4' ]; then
			mvn clean install -Dtest=Routines/Android/XO/Test/BVTTest4 -Dproperties=$CUSTOMER/$ENVIRONMENT -Dlocal=$LOCAL -Ddevice=$DEVICE -DtestRail.testPlanId=$XOTESTID -DtestRail.enabled=$TESTRAIL
		else 
			echo "Cannot find $CUSTOMER Test Suite $TEST for $PLATFORM"
		fi
	else 
		echo "Cannot find $PLATFORM Test Suite $TEST for $CUSTOMER"
	fi	
else 
	echo "Customer $CUSTOMER does not exist"
fi
exit
