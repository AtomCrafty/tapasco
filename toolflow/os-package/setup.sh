export TAPASCO_HOME=/opt/tapasco
echo "TAPASCO_HOME=${TAPASCO_HOME}"
export TAPASCO_HOME_TOOLFLOW=${TAPASCO_HOME}
echo "TAPASCO_HOME_TOOLFLOW=${TAPASCO_HOME_TOOLFLOW}"
export TAPASCO_HOME_TCL=${TAPASCO_HOME_TOOLFLOW}/vivado
echo "TAPASCO_HOME_TCL=${TAPASCO_HOME_TCL}"
export TAPASCO_HOME_RUNTIME=${TAPASCO_HOME}/runtime
echo "TAPASCO_HOME_RUNTIME=${TAPASCO_HOME_RUNTIME}"
export TAPASCO_WORK_DIR=$PWD
echo "TAPASCO_WORK_DIR=${TAPASCO_WORK_DIR}"

export PATH=${TAPASCO_HOME_TOOLFLOW}/bin:$PATH
#export MANPATH=$MANPATH:$TAPASCO_HOME/man
export MYVIVADO=$MYVIVADO:${TAPASCO_HOME_TCL}/common
export XILINX_PATH=$XILINX_PATH:${TAPASCO_HOME_TCL}/common