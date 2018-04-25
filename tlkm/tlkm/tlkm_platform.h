//
// Copyright (C) 2014-2018 Jens Korinth, TU Darmstadt
//
// This file is part of Tapasco (TaPaSCo).
//
// Tapasco is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Tapasco is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with Tapasco.  If not, see <http://www.gnu.org/licenses/>.
//
//! @file	tlkm_platform.h
//! @brief	Global configuration parameters for TLKM devices.
//! authors	J. Korinth, TU Darmstadt (jk@esa.cs.tu-darmstadt.de)
//!
#ifndef TLKM_PLATFORM_H__
#define TLKM_PLATFORM_H__

#include <platform_global.h>

struct tlkm_device;

struct platform_regspace {
	uintptr_t			base;
	uintptr_t			high;
	size_t				size;
};

#define INIT_REGSPACE(BASE, SIZE)	{ \
	.base				= (BASE), \
	.size				= (SIZE), \
	.high				= ((BASE) + (SIZE) - 1), \
}

struct platform {
	struct platform_regspace	status;
	struct platform_regspace	arch;
	struct platform_regspace	plat;
};

#define INIT_PLATFORM(status_base, status_size, arch_base, arch_size, plat_base, plat_size) \
	{ \
		.status = INIT_REGSPACE((status_base), (status_size)), \
		.arch   = INIT_REGSPACE((arch_base), (arch_size)), \
		.plat   = INIT_REGSPACE((plat_base), (plat_size)), \
	}

struct platform_mmap {
	void __iomem			*status;
	void __iomem			*arch;
	void __iomem			*plat;
};

int  tlkm_platform_mmap_init(struct tlkm_device *dev, struct platform_mmap *mmap);
void tlkm_platform_mmap_exit(struct tlkm_device *dev, struct platform_mmap *mmap);

#endif /* TLKM_PLATFORM_H__ */
